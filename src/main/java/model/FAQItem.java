package model;

public class FAQItem {
    private String  answer;
    private String question;

    public FAQItem(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {return this.answer;}
    public String getQuestion() {return this.question;}

}
