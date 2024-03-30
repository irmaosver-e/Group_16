package model;

import java.time.DateTimeException;

public class Inquiry {
    public String getSubject(){return m_subject; }
    private String m_subject;

    private DateTimeException createdAt;

    private String inquirerEmail;

    private String content;

    private String assignedTo;
    public Inquiry (String inquirerEmail, String subject, String content){
        super();
    }
    public String getInquirerEmail(){
        return inquirerEmail;
    }

    public String getEmailContent() {
        return content;
    }
}
