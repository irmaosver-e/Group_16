package model;

import java.util.ArrayList;

public class FAQSection {
    private Boolean isPrivate;
    private final String topic;
    public ArrayList<FAQItem> FAQItems;
    private ArrayList<FAQSection> subSections;

    private  FAQSection parent;

    public FAQSection(String topic){
        this.topic = topic;
        this.isPrivate = false;
        this.FAQItems = new ArrayList<FAQItem>();
        this.subSections = new ArrayList<FAQSection>();
    }

    public FAQSection(String topic, Boolean isPrivate){
        this.topic = topic;
        this.isPrivate = isPrivate;
        this.FAQItems = new ArrayList<FAQItem>();
        this.subSections = new ArrayList<FAQSection>();
    }

    public String getTopic() {return this.topic;}
    public Boolean getIsPrivate() {return this.isPrivate;}
    public FAQSection getParent() {return this.parent;}
    public void addsubSection(FAQSection sub) {subSections.add(sub);}
    public void addItem(String question, String answer){
        FAQItem newitem = new FAQItem(question, answer);
        this.FAQItems.add(newitem);
    }

    public ArrayList<FAQSection> getSubSections() {return this.subSections;}
    public ArrayList<FAQItem> getQApairs() {return this.FAQItems;}

    public String printFAQs() {

        return null;
    }
}
