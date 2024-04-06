package model;

public class Inquiry {

    private String inquirerEmail;
    private String subject;
    private String content;

    public Inquiry(String inquirerEmail, String subject, String content) {
        this.inquirerEmail = inquirerEmail;
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {return subject;}

    public String getInquirerEmail() { return inquirerEmail;}

    public String getEmailContent() { return content;}
}
