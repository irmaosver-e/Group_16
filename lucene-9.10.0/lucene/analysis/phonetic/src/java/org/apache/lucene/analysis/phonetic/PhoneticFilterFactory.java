/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.analysis.phonetic;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.language.Caverphone2;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Nysiis;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;
import org.apache.lucene.analysis.TokenFilterFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.ResourceLoader;
import org.apache.lucene.util.ResourceLoaderAware;

/**
 * Factory for {@link PhoneticFilter}.
 *
 * <p>Create tokens based on phonetic encoders from <a
 * href="http://commons.apache.org/codec/api-release/org/apache/commons/codec/language/package-summary.html">Apache
 * Commons Codec</a>.
 *
 * <p>This takes one required argument, "encoder", and the rest are optional:
 *
 * <dl>
 *   <dt>encoder
 *   <dd>required, one of "DoubleMetaphone", "Metaphone", "Soundex", "RefinedSoundex", "Caverphone"
 *       (v2.0), "ColognePhonetic" or "Nysiis" (case insensitive). If encoder isn't one of these,
 *       it'll be resolved as a class name either by itself if it already contains a '.' or
 *       otherwise as in the same package as these others.
 *   <dt>inject
 *   <dd>(default=true) add tokens to the stream with the offset=0
 *   <dt>maxCodeLength
 *   <dd>The maximum length of the phonetic codes, as defined by the encoder. If an encoder doesn't
 *       support this then specifying this is an error.
 * </dl>
 *
 * <pre class="prettyprint">
 * &lt;fieldType name="text_phonetic" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.PhoneticFilterFactory" encoder="DoubleMetaphone" inject="true"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 *
 * @see PhoneticFilter
 * @since 3.1
 * @lucene.spi {@value #NAME}
 */
public class PhoneticFilterFactory extends TokenFilterFactory implements ResourceLoaderAware {

  /** SPI name */
  public static final String NAME = "phonetic";

  /** parameter name: either a short name or a full class name */
  public static final String ENCODER = "encoder";

  /** parameter name: true if encoded tokens should be added as synonyms */
  public static final String INJECT = "inject"; // boolean

  /** parameter name: restricts the length of the phonetic code */
  public static final String MAX_CODE_LENGTH = "maxCodeLength";

  private static final String PACKAGE_CONTAINING_ENCODERS = "org.apache.commons.codec.language.";

  private static final Map<String, Class<? extends Encoder>> registry =
      Map.of(
          "DoubleMetaphone".toUpperCase(Locale.ROOT), DoubleMetaphone.class,
          "Metaphone".toUpperCase(Locale.ROOT), Metaphone.class,
          "Soundex".toUpperCase(Locale.ROOT), Soundex.class,
          "RefinedSoundex".toUpperCase(Locale.ROOT), RefinedSoundex.class,
          "Caverphone".toUpperCase(Locale.ROOT), Caverphone2.class,
          "ColognePhonetic".toUpperCase(Locale.ROOT), ColognePhonetic.class,
          "Nysiis".toUpperCase(Locale.ROOT), Nysiis.class);

  final boolean inject; // accessed by the test
  private final String name;
  private final Integer maxCodeLength;
  private Class<? extends Encoder> clazz = null;
  private Method setMaxCodeLenMethod = null;

  /** Creates a new PhoneticFilterFactory */
  public PhoneticFilterFactory(Map<String, String> args) {
    super(args);
    inject = getBoolean(args, INJECT, true);
    name = require(args, ENCODER);
    String v = get(args, MAX_CODE_LENGTH);
    if (v != null) {
      maxCodeLength = Integer.valueOf(v);
    } else {
      maxCodeLength = null;
    }
    if (!args.isEmpty()) {
      throw new IllegalArgumentException("Unknown parameters: " + args);
    }
  }

  /** Default ctor for compatibility with SPI */
  public PhoneticFilterFactory() {
    throw defaultCtorException();
  }

  @Override
  public void inform(ResourceLoader loader) throws IOException {
    clazz = registry.get(name.toUpperCase(Locale.ROOT));
    if (clazz == null) {
      clazz = resolveEncoder(name, loader);
    }

    if (maxCodeLength != null) {
      try {
        setMaxCodeLenMethod = clazz.getMethod("setMaxCodeLen", int.class);
      } catch (Exception e) {
        throw new IllegalArgumentException(
            "Encoder " + name + " / " + clazz + " does not support " + MAX_CODE_LENGTH, e);
      }
    }

    getEncoder(); // trigger initialization for potential problems to be thrown now
  }

  private Class<? extends Encoder> resolveEncoder(String name, ResourceLoader loader) {
    String lookupName = name;
    if (name.indexOf('.') == -1) {
      lookupName = PACKAGE_CONTAINING_ENCODERS + name;
    }
    try {
      return loader.newInstance(lookupName, Encoder.class).getClass();
    } catch (RuntimeException e) {
      throw new IllegalArgumentException(
          "Error loading encoder '"
              + name
              + "': must be full class name or one of "
              + registry.keySet(),
          e);
    }
  }

  /** Must be thread-safe. */
  protected Encoder getEncoder() {
    // Unfortunately, Commons-Codec doesn't offer any thread-safe guarantees so we must play it safe
    // and instantiate
    // every time.  A simple benchmark showed this as negligible.
    try {
      Encoder encoder = clazz.getConstructor().newInstance();
      // Try to set the maxCodeLength
      if (maxCodeLength != null && setMaxCodeLenMethod != null) {
        setMaxCodeLenMethod.invoke(encoder, maxCodeLength);
      }
      return encoder;
    } catch (Exception e) {
      final Throwable t = (e instanceof InvocationTargetException) ? e.getCause() : e;
      throw new IllegalArgumentException("Error initializing encoder: " + name + " / " + clazz, t);
    }
  }

  @Override
  public PhoneticFilter create(TokenStream input) {
    return new PhoneticFilter(input, getEncoder(), inject);
  }
}
