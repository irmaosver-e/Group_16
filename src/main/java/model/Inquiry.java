package model;

import java.time.DateTimeException;

public class Inquiry {

    private String subject;

    private DateTimeException createdAt;

    private String inquirerEmail;

    private String content;

    private String assignedTo;

    public Inquiry (String inquirerEmail, String subject, String content){
        this.inquirerEmail = inquirerEmail;
        this.subject = subject;
        this.content = content;
    }

    public String getSubject(){return subject; }
    public String getInquirerEmail(){
        return inquirerEmail;
    }

    public String getEmailContent() {
        return content;
    }
}
