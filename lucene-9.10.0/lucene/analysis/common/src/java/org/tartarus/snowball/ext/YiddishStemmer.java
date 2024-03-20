// Generated by Snowball 2.0.0 - https://snowballstem.org/

package org.tartarus.snowball.ext;

import org.tartarus.snowball.Among;

/**
 * This class implements the stemming algorithm defined by a snowball script.
 *
 * <p>Generated by Snowball 2.0.0 - https://snowballstem.org/
 */
@SuppressWarnings("unused")
public class YiddishStemmer extends org.tartarus.snowball.SnowballStemmer {

  private static final long serialVersionUID = 1L;
  private static final java.lang.invoke.MethodHandles.Lookup methodObject =
      java.lang.invoke.MethodHandles.lookup();

  private static final Among a_0[] = {
    new Among("\u05D0\u05D3\u05D5\u05E8\u05DB", -1, 1),
    new Among("\u05D0\u05D4\u05D9\u05E0", -1, 1),
    new Among("\u05D0\u05D4\u05E2\u05E8", -1, 1),
    new Among("\u05D0\u05D4\u05F2\u05DE", -1, 1),
    new Among("\u05D0\u05D5\u05DE", -1, 1),
    new Among("\u05D0\u05D5\u05E0\u05D8\u05E2\u05E8", -1, 1),
    new Among("\u05D0\u05D9\u05D1\u05E2\u05E8", -1, 1),
    new Among("\u05D0\u05E0", -1, 1),
    new Among("\u05D0\u05E0\u05D8", 7, 1),
    new Among("\u05D0\u05E0\u05D8\u05E7\u05E2\u05D2\u05E0", 8, 1),
    new Among("\u05D0\u05E0\u05D9\u05D3\u05E2\u05E8", 7, 1),
    new Among("\u05D0\u05E4", -1, 1),
    new Among("\u05D0\u05E4\u05D9\u05E8", 11, 1),
    new Among("\u05D0\u05E7\u05E2\u05D2\u05E0", -1, 1),
    new Among("\u05D0\u05E8\u05D0\u05E4", -1, 1),
    new Among("\u05D0\u05E8\u05D5\u05DE", -1, 1),
    new Among("\u05D0\u05E8\u05D5\u05E0\u05D8\u05E2\u05E8", -1, 1),
    new Among("\u05D0\u05E8\u05D9\u05D1\u05E2\u05E8", -1, 1),
    new Among("\u05D0\u05E8\u05F1\u05E1", -1, 1),
    new Among("\u05D0\u05E8\u05F1\u05E4", -1, 1),
    new Among("\u05D0\u05E8\u05F2\u05E0", -1, 1),
    new Among("\u05D0\u05F0\u05E2\u05E7", -1, 1),
    new Among("\u05D0\u05F1\u05E1", -1, 1),
    new Among("\u05D0\u05F1\u05E4", -1, 1),
    new Among("\u05D0\u05F2\u05E0", -1, 1),
    new Among("\u05D1\u05D0", -1, 1),
    new Among("\u05D1\u05F2", -1, 1),
    new Among("\u05D3\u05D5\u05E8\u05DB", -1, 1),
    new Among("\u05D3\u05E2\u05E8", -1, 1),
    new Among("\u05DE\u05D9\u05D8", -1, 1),
    new Among("\u05E0\u05D0\u05DB", -1, 1),
    new Among("\u05E4\u05D0\u05E8", -1, 1),
    new Among("\u05E4\u05D0\u05E8\u05D1\u05F2", 31, 1),
    new Among("\u05E4\u05D0\u05E8\u05F1\u05E1", 31, 1),
    new Among("\u05E4\u05D5\u05E0\u05D0\u05E0\u05D3\u05E2\u05E8", -1, 1),
    new Among("\u05E6\u05D5", -1, 1),
    new Among("\u05E6\u05D5\u05D6\u05D0\u05DE\u05E2\u05E0", 35, 1),
    new Among("\u05E6\u05D5\u05E0\u05F1\u05E4", 35, 1),
    new Among("\u05E6\u05D5\u05E8\u05D9\u05E7", 35, 1),
    new Among("\u05E6\u05E2", -1, 1)
  };

  private static final Among a_1[] = {
    new Among("\u05D3\u05D6\u05E9", -1, -1),
    new Among("\u05E9\u05D8\u05E8", -1, -1),
    new Among("\u05E9\u05D8\u05E9", -1, -1),
    new Among("\u05E9\u05E4\u05E8", -1, -1)
  };

  private static final Among a_2[] = {
    new Among("\u05D5\u05E0\u05D2", -1, 1),
    new Among("\u05E1\u05D8\u05D5", -1, 1),
    new Among("\u05D8", -1, 1),
    new Among("\u05D1\u05E8\u05D0\u05DB\u05D8", 2, 31),
    new Among("\u05E1\u05D8", 2, 1),
    new Among("\u05D9\u05E1\u05D8", 4, 1),
    new Among("\u05D2\u05D9\u05E1\u05D8", 5, 33),
    new Among("\u05E9\u05D9\u05E1\u05D8", 5, 33),
    new Among("\u05E9\u05D0\u05E4\u05D8", 2, 1),
    new Among("\u05D4\u05F2\u05D8", 2, 1),
    new Among("\u05E7\u05F2\u05D8", 2, 1),
    new Among("\u05D9\u05E7\u05F2\u05D8", 10, 1),
    new Among("\u05DC\u05E2\u05DB", -1, 1),
    new Among("\u05E2\u05DC\u05E2\u05DB", 12, 1),
    new Among("\u05D9\u05D6\u05DE", -1, 1),
    new Among("\u05D9\u05DE", -1, 1),
    new Among("\u05E2\u05DE", -1, 1),
    new Among("\u05E2\u05E0\u05E2\u05DE", 16, 3),
    new Among("\u05D8\u05E2\u05E0\u05E2\u05DE", 17, 4),
    new Among("\u05E0", -1, 1),
    new Among("\u05E7\u05DC\u05D9\u05D1\u05E0", 19, 14),
    new Among("\u05E8\u05D9\u05D1\u05E0", 19, 15),
    new Among("\u05D8\u05E8\u05D9\u05D1\u05E0", 21, 12),
    new Among("\u05E9\u05E8\u05D9\u05D1\u05E0", 21, 7),
    new Among("\u05D4\u05F1\u05D1\u05E0", 19, 27),
    new Among("\u05E9\u05F0\u05D9\u05D2\u05E0", 19, 17),
    new Among("\u05D6\u05D5\u05E0\u05D2\u05E0", 19, 22),
    new Among("\u05E9\u05DC\u05D5\u05E0\u05D2\u05E0", 19, 25),
    new Among("\u05E6\u05F0\u05D5\u05E0\u05D2\u05E0", 19, 24),
    new Among("\u05D1\u05F1\u05D2\u05E0", 19, 26),
    new Among("\u05D1\u05D5\u05E0\u05D3\u05E0", 19, 20),
    new Among("\u05F0\u05D9\u05D6\u05E0", 19, 11),
    new Among("\u05D8\u05E0", 19, 4),
    new Among("GE\u05D1\u05D9\u05D8\u05E0", 32, 9),
    new Among("GE\u05DC\u05D9\u05D8\u05E0", 32, 13),
    new Among("GE\u05DE\u05D9\u05D8\u05E0", 32, 8),
    new Among("\u05E9\u05E0\u05D9\u05D8\u05E0", 32, 19),
    new Among("\u05E1\u05D8\u05E0", 32, 1),
    new Among("\u05D9\u05E1\u05D8\u05E0", 37, 1),
    new Among("GE\u05D1\u05D9\u05E1\u05E0", 19, 10),
    new Among("\u05E9\u05DE\u05D9\u05E1\u05E0", 19, 18),
    new Among("GE\u05E8\u05D9\u05E1\u05E0", 19, 16),
    new Among("\u05E2\u05E0", 19, 1),
    new Among("\u05D2\u05D0\u05E0\u05D2\u05E2\u05E0", 42, 5),
    new Among("\u05E2\u05DC\u05E2\u05E0", 42, 1),
    new Among("\u05E0\u05D5\u05DE\u05E2\u05E0", 42, 6),
    new Among("\u05D9\u05D6\u05DE\u05E2\u05E0", 42, 1),
    new Among("\u05E9\u05D8\u05D0\u05E0\u05E2\u05E0", 42, 29),
    new Among("\u05D8\u05E8\u05D5\u05E0\u05E7\u05E0", 19, 23),
    new Among("\u05E4\u05D0\u05E8\u05DC\u05F1\u05E8\u05E0", 19, 28),
    new Among("\u05E9\u05F0\u05F1\u05E8\u05E0", 19, 30),
    new Among("\u05F0\u05D5\u05D8\u05E9\u05E0", 19, 21),
    new Among("\u05D2\u05F2\u05E0", 19, 5),
    new Among("\u05E1", -1, 1),
    new Among("\u05D8\u05E1", 53, 4),
    new Among("\u05E0\u05E1", 53, 1),
    new Among("\u05D8\u05E0\u05E1", 55, 4),
    new Among("\u05E2\u05E0\u05E1", 55, 3),
    new Among("\u05E2\u05E1", 53, 1),
    new Among("\u05D9\u05E2\u05E1", 58, 2),
    new Among("\u05E2\u05DC\u05E2\u05E1", 58, 1),
    new Among("\u05E2\u05E8\u05E1", 53, 1),
    new Among("\u05E2\u05E0\u05E2\u05E8\u05E1", 61, 1),
    new Among("\u05E2", -1, 1),
    new Among("\u05D8\u05E2", 63, 4),
    new Among("\u05E1\u05D8\u05E2", 64, 1),
    new Among("\u05D9\u05E2", 63, -1),
    new Among("\u05E2\u05DC\u05E2", 63, 1),
    new Among("\u05E2\u05E0\u05E2", 63, 3),
    new Among("\u05D8\u05E2\u05E0\u05E2", 68, 4),
    new Among("\u05E2\u05E8", -1, 1),
    new Among("\u05D8\u05E2\u05E8", 70, 4),
    new Among("\u05E1\u05D8\u05E2\u05E8", 71, 1),
    new Among("\u05E2\u05E0\u05E2\u05E8", 70, 3),
    new Among("\u05D8\u05E2\u05E0\u05E2\u05E8", 73, 4),
    new Among("\u05D5\u05EA", -1, 32)
  };

  private static final Among a_3[] = {
    new Among("\u05D5\u05E0\u05D2", -1, 1),
    new Among("\u05E9\u05D0\u05E4\u05D8", -1, 1),
    new Among("\u05D4\u05F2\u05D8", -1, 1),
    new Among("\u05E7\u05F2\u05D8", -1, 1),
    new Among("\u05D9\u05E7\u05F2\u05D8", 3, 1),
    new Among("\u05DC", -1, 2)
  };

  private static final Among a_4[] = {
    new Among("\u05D9\u05D2", -1, 1),
    new Among("\u05D9\u05E7", -1, 1),
    new Among("\u05D3\u05D9\u05E7", 1, 1),
    new Among("\u05E0\u05D3\u05D9\u05E7", 2, 1),
    new Among("\u05E2\u05E0\u05D3\u05D9\u05E7", 3, 2),
    new Among("\u05D1\u05DC\u05D9\u05E7", 1, -1),
    new Among("\u05D2\u05DC\u05D9\u05E7", 1, -1),
    new Among("\u05E0\u05D9\u05E7", 1, 1),
    new Among("\u05D9\u05E9", -1, 1)
  };

  private static final char g_niked[] = {255, 155, 6};

  private static final char g_vowel[] = {33, 2, 4, 0, 6};

  private static final char g_consonant[] = {239, 254, 253, 131};

  private int I_x;
  private int I_p1;

  private boolean r_prelude() {
    int v_1 = cursor;
    lab0:
    {
      while (true) {
        int v_2 = cursor;
        lab1:
        {
          golab2:
          while (true) {
            int v_3 = cursor;
            lab3:
            {
              lab4:
              {
                int v_4 = cursor;
                lab5:
                {
                  bra = cursor;
                  if (!(eq_s("\u05D5\u05D5"))) {
                    break lab5;
                  }
                  ket = cursor;
                  {
                    int v_5 = cursor;
                    lab6:
                    {
                      if (!(eq_s("\u05BC"))) {
                        break lab6;
                      }
                      break lab5;
                    }
                    cursor = v_5;
                  }
                  slice_from("\u05F0");
                  break lab4;
                }
                cursor = v_4;
                lab7:
                {
                  bra = cursor;
                  if (!(eq_s("\u05D5\u05D9"))) {
                    break lab7;
                  }
                  ket = cursor;
                  {
                    int v_6 = cursor;
                    lab8:
                    {
                      if (!(eq_s("\u05B4"))) {
                        break lab8;
                      }
                      break lab7;
                    }
                    cursor = v_6;
                  }
                  slice_from("\u05F1");
                  break lab4;
                }
                cursor = v_4;
                lab9:
                {
                  bra = cursor;
                  if (!(eq_s("\u05D9\u05D9"))) {
                    break lab9;
                  }
                  ket = cursor;
                  {
                    int v_7 = cursor;
                    lab10:
                    {
                      if (!(eq_s("\u05B4"))) {
                        break lab10;
                      }
                      break lab9;
                    }
                    cursor = v_7;
                  }
                  slice_from("\u05F2");
                  break lab4;
                }
                cursor = v_4;
                lab11:
                {
                  bra = cursor;
                  if (!(eq_s("\u05DA"))) {
                    break lab11;
                  }
                  ket = cursor;
                  slice_from("\u05DB");
                  break lab4;
                }
                cursor = v_4;
                lab12:
                {
                  bra = cursor;
                  if (!(eq_s("\u05DD"))) {
                    break lab12;
                  }
                  ket = cursor;
                  slice_from("\u05DE");
                  break lab4;
                }
                cursor = v_4;
                lab13:
                {
                  bra = cursor;
                  if (!(eq_s("\u05DF"))) {
                    break lab13;
                  }
                  ket = cursor;
                  slice_from("\u05E0");
                  break lab4;
                }
                cursor = v_4;
                lab14:
                {
                  bra = cursor;
                  if (!(eq_s("\u05E3"))) {
                    break lab14;
                  }
                  ket = cursor;
                  slice_from("\u05E4");
                  break lab4;
                }
                cursor = v_4;
                bra = cursor;
                if (!(eq_s("\u05E5"))) {
                  break lab3;
                }
                ket = cursor;
                slice_from("\u05E6");
              }
              cursor = v_3;
              break golab2;
            }
            cursor = v_3;
            if (cursor >= limit) {
              break lab1;
            }
            cursor++;
          }
          continue;
        }
        cursor = v_2;
        break;
      }
    }
    cursor = v_1;
    int v_8 = cursor;
    lab15:
    {
      while (true) {
        int v_9 = cursor;
        lab16:
        {
          golab17:
          while (true) {
            int v_10 = cursor;
            lab18:
            {
              bra = cursor;
              if (!(in_grouping(g_niked, 1456, 1474))) {
                break lab18;
              }
              ket = cursor;
              slice_del();
              cursor = v_10;
              break golab17;
            }
            cursor = v_10;
            if (cursor >= limit) {
              break lab16;
            }
            cursor++;
          }
          continue;
        }
        cursor = v_9;
        break;
      }
    }
    cursor = v_8;
    return true;
  }

  private boolean r_mark_regions() {
    I_p1 = limit;
    int v_1 = cursor;
    lab0:
    {
      bra = cursor;
      if (!(eq_s("\u05D2\u05E2"))) {
        cursor = v_1;
        break lab0;
      }
      ket = cursor;
      slice_from("GE");
    }
    int v_2 = cursor;
    lab1:
    {
      if (find_among(a_0) == 0) {
        cursor = v_2;
        break lab1;
      }
      lab2:
      {
        int v_3 = cursor;
        lab3:
        {
          lab4:
          {
            int v_4 = cursor;
            lab5:
            {
              if (!(eq_s("\u05E6\u05D5\u05D2\u05E0"))) {
                break lab5;
              }
              break lab4;
            }
            cursor = v_4;
            lab6:
            {
              if (!(eq_s("\u05E6\u05D5\u05E7\u05D8"))) {
                break lab6;
              }
              break lab4;
            }
            cursor = v_4;
            if (!(eq_s("\u05E6\u05D5\u05E7\u05E0"))) {
              break lab3;
            }
          }
          {
            int v_5 = cursor;
            lab7:
            {
              {
                int c = cursor + 1;
                if (0 > c || c > limit) {
                  break lab7;
                }
                cursor = c;
              }
              break lab3;
            }
            cursor = v_5;
          }
          {
            int c = cursor + -4;
            if (0 > c || c > limit) {
              break lab3;
            }
            cursor = c;
          }
          break lab2;
        }
        cursor = v_3;
        lab8:
        {
          bra = cursor;
          if (!(eq_s("\u05D2\u05E2"))) {
            break lab8;
          }
          ket = cursor;
          slice_from("GE");
          break lab2;
        }
        cursor = v_3;
        bra = cursor;
        if (!(eq_s("\u05E6\u05D5"))) {
          cursor = v_2;
          break lab1;
        }
        ket = cursor;
        slice_from("TSU");
      }
    }
    int v_6 = cursor;
    {
      int c = cursor + 3;
      if (0 > c || c > limit) {
        return false;
      }
      cursor = c;
    }
    I_x = cursor;
    cursor = v_6;
    int v_7 = cursor;
    lab9:
    {
      if (find_among(a_1) == 0) {
        cursor = v_7;
        break lab9;
      }
    }
    {
      int v_8 = cursor;
      lab10:
      {
        if (!(in_grouping(g_consonant, 1489, 1520))) {
          break lab10;
        }
        if (!(in_grouping(g_consonant, 1489, 1520))) {
          break lab10;
        }
        if (!(in_grouping(g_consonant, 1489, 1520))) {
          break lab10;
        }
        I_p1 = cursor;
        return false;
      }
      cursor = v_8;
    }
    golab11:
    while (true) {
      int v_9 = cursor;
      lab12:
      {
        if (!(in_grouping(g_vowel, 1488, 1522))) {
          break lab12;
        }
        cursor = v_9;
        break golab11;
      }
      cursor = v_9;
      if (cursor >= limit) {
        return false;
      }
      cursor++;
    }
    while (true) {
      lab13:
      {
        if (!(in_grouping(g_vowel, 1488, 1522))) {
          break lab13;
        }
        continue;
      }
      break;
    }
    I_p1 = cursor;
    lab14:
    {
      if (!(I_p1 < I_x)) {
        break lab14;
      }
      I_p1 = I_x;
    }
    return true;
  }

  private boolean r_R1() {
    if (!(I_p1 <= cursor)) {
      return false;
    }
    return true;
  }

  private boolean r_standard_suffix() {
    int among_var;
    int v_1 = limit - cursor;
    lab0:
    {
      ket = cursor;
      among_var = find_among_b(a_2);
      if (among_var == 0) {
        break lab0;
      }
      bra = cursor;
      switch (among_var) {
        case 1:
          if (!r_R1()) {
            break lab0;
          }
          slice_del();
          break;
        case 2:
          if (!r_R1()) {
            break lab0;
          }
          slice_from("\u05D9\u05E2");
          break;
        case 3:
          if (!r_R1()) {
            break lab0;
          }
          slice_del();
          {
            int v_2 = limit - cursor;
            lab1:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D2\u05D0\u05E0\u05D2"))) {
                break lab1;
              }
              bra = cursor;
              slice_from("\u05D2\u05F2");
              break lab0;
            }
            cursor = limit - v_2;
          }
          {
            int v_3 = limit - cursor;
            lab2:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E0\u05D5\u05DE"))) {
                break lab2;
              }
              bra = cursor;
              slice_from("\u05E0\u05E2\u05DE");
              break lab0;
            }
            cursor = limit - v_3;
          }
          {
            int v_4 = limit - cursor;
            lab3:
            {
              ket = cursor;
              if (!(eq_s_b("\u05DE\u05D9\u05D8"))) {
                break lab3;
              }
              bra = cursor;
              slice_from("\u05DE\u05F2\u05D3");
              break lab0;
            }
            cursor = limit - v_4;
          }
          {
            int v_5 = limit - cursor;
            lab4:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D1\u05D9\u05D8"))) {
                break lab4;
              }
              bra = cursor;
              slice_from("\u05D1\u05F2\u05D8");
              break lab0;
            }
            cursor = limit - v_5;
          }
          {
            int v_6 = limit - cursor;
            lab5:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D1\u05D9\u05E1"))) {
                break lab5;
              }
              bra = cursor;
              slice_from("\u05D1\u05F2\u05E1");
              break lab0;
            }
            cursor = limit - v_6;
          }
          {
            int v_7 = limit - cursor;
            lab6:
            {
              ket = cursor;
              if (!(eq_s_b("\u05F0\u05D9\u05D6"))) {
                break lab6;
              }
              bra = cursor;
              slice_from("\u05F0\u05F2\u05D6");
              break lab0;
            }
            cursor = limit - v_7;
          }
          {
            int v_8 = limit - cursor;
            lab7:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D8\u05E8\u05D9\u05D1"))) {
                break lab7;
              }
              bra = cursor;
              slice_from("\u05D8\u05E8\u05F2\u05D1");
              break lab0;
            }
            cursor = limit - v_8;
          }
          {
            int v_9 = limit - cursor;
            lab8:
            {
              ket = cursor;
              if (!(eq_s_b("\u05DC\u05D9\u05D8"))) {
                break lab8;
              }
              bra = cursor;
              slice_from("\u05DC\u05F2\u05D8");
              break lab0;
            }
            cursor = limit - v_9;
          }
          {
            int v_10 = limit - cursor;
            lab9:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E7\u05DC\u05D9\u05D1"))) {
                break lab9;
              }
              bra = cursor;
              slice_from("\u05E7\u05DC\u05F2\u05D1");
              break lab0;
            }
            cursor = limit - v_10;
          }
          {
            int v_11 = limit - cursor;
            lab10:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E8\u05D9\u05D1"))) {
                break lab10;
              }
              bra = cursor;
              slice_from("\u05E8\u05F2\u05D1");
              break lab0;
            }
            cursor = limit - v_11;
          }
          {
            int v_12 = limit - cursor;
            lab11:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E8\u05D9\u05E1"))) {
                break lab11;
              }
              bra = cursor;
              slice_from("\u05E8\u05F2\u05E1");
              break lab0;
            }
            cursor = limit - v_12;
          }
          {
            int v_13 = limit - cursor;
            lab12:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05F0\u05D9\u05D2"))) {
                break lab12;
              }
              bra = cursor;
              slice_from("\u05E9\u05F0\u05F2\u05D2");
              break lab0;
            }
            cursor = limit - v_13;
          }
          {
            int v_14 = limit - cursor;
            lab13:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05DE\u05D9\u05E1"))) {
                break lab13;
              }
              bra = cursor;
              slice_from("\u05E9\u05DE\u05F2\u05E1");
              break lab0;
            }
            cursor = limit - v_14;
          }
          {
            int v_15 = limit - cursor;
            lab14:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05E0\u05D9\u05D8"))) {
                break lab14;
              }
              bra = cursor;
              slice_from("\u05E9\u05E0\u05F2\u05D3");
              break lab0;
            }
            cursor = limit - v_15;
          }
          {
            int v_16 = limit - cursor;
            lab15:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05E8\u05D9\u05D1"))) {
                break lab15;
              }
              bra = cursor;
              slice_from("\u05E9\u05E8\u05F2\u05D1");
              break lab0;
            }
            cursor = limit - v_16;
          }
          {
            int v_17 = limit - cursor;
            lab16:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D1\u05D5\u05E0\u05D3"))) {
                break lab16;
              }
              bra = cursor;
              slice_from("\u05D1\u05D9\u05E0\u05D3");
              break lab0;
            }
            cursor = limit - v_17;
          }
          {
            int v_18 = limit - cursor;
            lab17:
            {
              ket = cursor;
              if (!(eq_s_b("\u05F0\u05D5\u05D8\u05E9"))) {
                break lab17;
              }
              bra = cursor;
              slice_from("\u05F0\u05D9\u05D8\u05E9");
              break lab0;
            }
            cursor = limit - v_18;
          }
          {
            int v_19 = limit - cursor;
            lab18:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D6\u05D5\u05E0\u05D2"))) {
                break lab18;
              }
              bra = cursor;
              slice_from("\u05D6\u05D9\u05E0\u05D2");
              break lab0;
            }
            cursor = limit - v_19;
          }
          {
            int v_20 = limit - cursor;
            lab19:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D8\u05E8\u05D5\u05E0\u05E7"))) {
                break lab19;
              }
              bra = cursor;
              slice_from("\u05D8\u05E8\u05D9\u05E0\u05E7");
              break lab0;
            }
            cursor = limit - v_20;
          }
          {
            int v_21 = limit - cursor;
            lab20:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E6\u05F0\u05D5\u05E0\u05D2"))) {
                break lab20;
              }
              bra = cursor;
              slice_from("\u05E6\u05F0\u05D9\u05E0\u05D2");
              break lab0;
            }
            cursor = limit - v_21;
          }
          {
            int v_22 = limit - cursor;
            lab21:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05DC\u05D5\u05E0\u05D2"))) {
                break lab21;
              }
              bra = cursor;
              slice_from("\u05E9\u05DC\u05D9\u05E0\u05D2");
              break lab0;
            }
            cursor = limit - v_22;
          }
          {
            int v_23 = limit - cursor;
            lab22:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D1\u05F1\u05D2"))) {
                break lab22;
              }
              bra = cursor;
              slice_from("\u05D1\u05F2\u05D2");
              break lab0;
            }
            cursor = limit - v_23;
          }
          {
            int v_24 = limit - cursor;
            lab23:
            {
              ket = cursor;
              if (!(eq_s_b("\u05D4\u05F1\u05D1"))) {
                break lab23;
              }
              bra = cursor;
              slice_from("\u05D4\u05F2\u05D1");
              break lab0;
            }
            cursor = limit - v_24;
          }
          {
            int v_25 = limit - cursor;
            lab24:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E4\u05D0\u05E8\u05DC\u05F1\u05E8"))) {
                break lab24;
              }
              bra = cursor;
              slice_from("\u05E4\u05D0\u05E8\u05DC\u05D9\u05E8");
              break lab0;
            }
            cursor = limit - v_25;
          }
          {
            int v_26 = limit - cursor;
            lab25:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05D8\u05D0\u05E0"))) {
                break lab25;
              }
              bra = cursor;
              slice_from("\u05E9\u05D8\u05F2");
              break lab0;
            }
            cursor = limit - v_26;
          }
          {
            int v_27 = limit - cursor;
            lab26:
            {
              ket = cursor;
              if (!(eq_s_b("\u05E9\u05F0\u05F1\u05E8"))) {
                break lab26;
              }
              bra = cursor;
              slice_from("\u05E9\u05F0\u05E2\u05E8");
              break lab0;
            }
            cursor = limit - v_27;
          }
          break;
        case 4:
          {
            int v_28 = limit - cursor;
            lab27:
            {
              if (!r_R1()) {
                break lab27;
              }
              slice_del();
              {
                int v_29 = limit - cursor;
                lab28:
                {
                  ket = cursor;
                  lab29:
                  {
                    int v_30 = limit - cursor;
                    lab30:
                    {
                      if (!(eq_s_b("\u05D2\u05E2\u05D1\u05E8\u05D0\u05DB"))) {
                        break lab30;
                      }
                      break lab29;
                    }
                    cursor = limit - v_30;
                    if (!(eq_s_b("\u05D1\u05E8\u05D0\u05DB"))) {
                      break lab28;
                    }
                  }
                  bra = cursor;
                  slice_from("\u05D1\u05E8\u05E2\u05E0\u05D2");
                  break lab27;
                }
                cursor = limit - v_29;
              }
              break lab0;
            }
            cursor = limit - v_28;
          }
          while (true) {
            lab31:
            {
              {
                int c = cursor - -1;
                if (limit_backward > c || c > limit) {
                  break lab31;
                }
                cursor = c;
              }
              continue;
            }
            break;
          }
          ket = cursor;
          lab32:
          {
            int v_32 = limit - cursor;
            lab33:
            {
              if (!(eq_s_b("\u05E2\u05E0\u05E2\u05E8"))) {
                break lab33;
              }
              break lab32;
            }
            cursor = limit - v_32;
            lab34:
            {
              if (!(eq_s_b("\u05E2\u05E0\u05E2\u05DE"))) {
                break lab34;
              }
              break lab32;
            }
            cursor = limit - v_32;
            lab35:
            {
              if (!(eq_s_b("\u05E2\u05E0\u05E2"))) {
                break lab35;
              }
              break lab32;
            }
            cursor = limit - v_32;
            lab36:
            {
              if (!(eq_s_b("\u05E0"))) {
                break lab36;
              }
              break lab32;
            }
            cursor = limit - v_32;
            lab37:
            {
              if (!(eq_s_b("\u05E2\u05E8"))) {
                break lab37;
              }
              break lab32;
            }
            cursor = limit - v_32;
            lab38:
            {
              if (!(eq_s_b("\u05E0\u05E1"))) {
                break lab38;
              }
              break lab32;
            }
            cursor = limit - v_32;
            lab39:
            {
              if (!(eq_s_b("\u05E2"))) {
                break lab39;
              }
              break lab32;
            }
            cursor = limit - v_32;
            if (!(eq_s_b("\u05E1"))) {
              break lab0;
            }
          }
          bra = cursor;
          if (!r_R1()) {
            break lab0;
          }
          slice_del();
          break;
        case 5:
          slice_from("\u05D2\u05F2");
          break;
        case 6:
          slice_from("\u05E0\u05E2\u05DE");
          break;
        case 7:
          slice_from("\u05E9\u05E8\u05F2\u05D1");
          break;
        case 8:
          slice_from("\u05DE\u05F2\u05D3");
          break;
        case 9:
          slice_from("\u05D1\u05F2\u05D8");
          break;
        case 10:
          slice_from("\u05D1\u05F2\u05E1");
          break;
        case 11:
          slice_from("\u05F0\u05F2\u05D6");
          break;
        case 12:
          slice_from("\u05D8\u05E8\u05F2\u05D1");
          break;
        case 13:
          slice_from("\u05DC\u05F2\u05D8");
          break;
        case 14:
          slice_from("\u05E7\u05DC\u05F2\u05D1");
          break;
        case 15:
          slice_from("\u05E8\u05F2\u05D1");
          break;
        case 16:
          slice_from("\u05E8\u05F2\u05E1");
          break;
        case 17:
          slice_from("\u05E9\u05F0\u05F2\u05D2");
          break;
        case 18:
          slice_from("\u05E9\u05DE\u05F2\u05E1");
          break;
        case 19:
          slice_from("\u05E9\u05E0\u05F2\u05D3");
          break;
        case 20:
          slice_from("\u05D1\u05D9\u05E0\u05D3");
          break;
        case 21:
          slice_from("\u05F0\u05D9\u05D8\u05E9");
          break;
        case 22:
          slice_from("\u05D6\u05D9\u05E0\u05D2");
          break;
        case 23:
          slice_from("\u05D8\u05E8\u05D9\u05E0\u05E7");
          break;
        case 24:
          slice_from("\u05E6\u05F0\u05D9\u05E0\u05D2");
          break;
        case 25:
          slice_from("\u05E9\u05DC\u05D9\u05E0\u05D2");
          break;
        case 26:
          slice_from("\u05D1\u05F2\u05D2");
          break;
        case 27:
          slice_from("\u05D4\u05F2\u05D1");
          break;
        case 28:
          slice_from("\u05E4\u05D0\u05E8\u05DC\u05D9\u05E8");
          break;
        case 29:
          slice_from("\u05E9\u05D8\u05F2");
          break;
        case 30:
          slice_from("\u05E9\u05F0\u05E2\u05E8");
          break;
        case 31:
          slice_from("\u05D1\u05E8\u05E2\u05E0\u05D2");
          break;
        case 32:
          if (!r_R1()) {
            break lab0;
          }
          slice_from("\u05D4");
          break;
        case 33:
          {
            int c = cursor - -4;
            if (limit_backward > c || c > limit) {
              break lab0;
            }
            cursor = c;
          }
          ket = cursor;
          if (!(eq_s_b("\u05D8"))) {
            break lab0;
          }
          bra = cursor;
          if (!r_R1()) {
            break lab0;
          }
          slice_del();
          break;
      }
    }
    cursor = limit - v_1;
    int v_33 = limit - cursor;
    lab40:
    {
      ket = cursor;
      among_var = find_among_b(a_3);
      if (among_var == 0) {
        break lab40;
      }
      bra = cursor;
      switch (among_var) {
        case 1:
          if (!r_R1()) {
            break lab40;
          }
          slice_del();
          break;
        case 2:
          if (!r_R1()) {
            break lab40;
          }
          if (!(in_grouping_b(g_consonant, 1489, 1520))) {
            break lab40;
          }
          slice_del();
          break;
      }
    }
    cursor = limit - v_33;
    int v_34 = limit - cursor;
    lab41:
    {
      ket = cursor;
      among_var = find_among_b(a_4);
      if (among_var == 0) {
        break lab41;
      }
      bra = cursor;
      switch (among_var) {
        case 1:
          if (!r_R1()) {
            break lab41;
          }
          slice_del();
          break;
        case 2:
          if (!r_R1()) {
            break lab41;
          }
          {
            int v_35 = limit - cursor;
            lab42:
            {
              int v_36 = limit - cursor;
              lab43:
              {
                int v_37 = limit - cursor;
                lab44:
                {
                  if (!(eq_s_b("\u05E0\u05D2"))) {
                    break lab44;
                  }
                  break lab43;
                }
                cursor = limit - v_37;
                lab45:
                {
                  if (!(eq_s_b("\u05E0\u05E7"))) {
                    break lab45;
                  }
                  break lab43;
                }
                cursor = limit - v_37;
                lab46:
                {
                  if (!(eq_s_b("\u05E0"))) {
                    break lab46;
                  }
                  break lab43;
                }
                cursor = limit - v_37;
                lab47:
                {
                  if (!(eq_s_b("\u05DE"))) {
                    break lab47;
                  }
                  break lab43;
                }
                cursor = limit - v_37;
                lab48:
                {
                  if (!(eq_s_b("Lamed"))) {
                    break lab48;
                  }
                  if (!(in_grouping_b(g_consonant, 1489, 1520))) {
                    break lab48;
                  }
                  break lab43;
                }
                cursor = limit - v_37;
                if (!(in_grouping_b(g_vowel, 1488, 1522))) {
                  break lab42;
                }
              }
              cursor = limit - v_36;
              slice_del();
              break lab41;
            }
            cursor = limit - v_35;
          }
          {
            int c = cursor - -5;
            if (limit_backward > c || c > limit) {
              break lab41;
            }
            cursor = c;
          }
          if (!(eq_s_b("\u05E0\u05D3\u05D9\u05E7"))) {
            break lab41;
          }
          slice_del();
          break;
      }
    }
    cursor = limit - v_34;
    int v_38 = limit - cursor;
    lab49:
    {
      while (true) {
        int v_39 = limit - cursor;
        lab50:
        {
          golab51:
          while (true) {
            int v_40 = limit - cursor;
            lab52:
            {
              ket = cursor;
              lab53:
              {
                int v_41 = limit - cursor;
                lab54:
                {
                  if (!(eq_s_b("GE"))) {
                    break lab54;
                  }
                  break lab53;
                }
                cursor = limit - v_41;
                if (!(eq_s_b("TSU"))) {
                  break lab52;
                }
              }
              bra = cursor;
              slice_del();
              cursor = limit - v_40;
              break golab51;
            }
            cursor = limit - v_40;
            if (cursor <= limit_backward) {
              break lab50;
            }
            cursor--;
          }
          continue;
        }
        cursor = limit - v_39;
        break;
      }
    }
    cursor = limit - v_38;
    return true;
  }

  @Override
  public boolean stem() {
    r_prelude();
    int v_2 = cursor;
    r_mark_regions();
    cursor = v_2;
    limit_backward = cursor;
    cursor = limit;
    r_standard_suffix();
    cursor = limit_backward;
    return true;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof YiddishStemmer;
  }

  @Override
  public int hashCode() {
    return YiddishStemmer.class.getName().hashCode();
  }
}
