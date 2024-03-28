package model;

import java.time.DateTimeException;

public class Inquiry {
    public String getSubject(){return m_subject; }
    private String m_subject;

    private DateTimeException createdAt;

    private String inquirerEmail;

    private String content;

    private String assignedTo;
    //not sure if this is right though
    public Inquiry (String string1, String string2, String string3){
        super();
    }
    public String getInquirerEmail(){return inquirerEmail;}

    public String getEmailContent(){return content;}
}
