package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public FAQSection() {
        this.topic = null;
        this.isPrivate = false;
        this.FAQItems = new ArrayList<FAQItem>();
        this.subSections = new ArrayList<FAQSection>();
    }


    public void addsubSection(FAQSection sub) {
        sub.parent = this;
        subSections.add(sub);
    }
    public void addItem(String question, String answer){
        FAQItem newitem = new FAQItem(question, answer);
        this.FAQItems.add(newitem);
    }

    public void setParent(FAQSection parent) {
        this.parent = parent;
    }

    public ArrayList<FAQItem> getFAQItems() {
        return this.FAQItems;
    }
    public String getTopic() {return this.topic;}
    public Boolean getIsPrivate() {return this.isPrivate;}
    public FAQSection getParent() {return this.parent;}
    public ArrayList<String> getAllSubTopics(){
        ArrayList<String> subSectionsNames = new ArrayList<>();
        for(FAQSection subSection:this.subSections){
            subSectionsNames.add(subSection.getTopic());
        }
        return subSectionsNames;
    }

    public void addSubsection(FAQSection subSection){
        subSection.parent = this;
        this.subSections.add(subSection);
    }
    public FAQSection getSubSectionWithTopic(String topic){
        for(FAQSection subSection:this.subSections){
            if(subSection.getTopic().equals(topic)){
                return subSection;
            }
        }
        return null;
    }
    public ArrayList<String> getSuperTopics(){
        if (this.topic == null){ // this at root
            return null;
        }else if(this.parent == null || this.parent.getTopic() == null){ // this parent at root
            return new ArrayList<>(List.of("root"));
        }else if(this.parent != null){
            ArrayList<FAQSection> superSections = this.parent.getParent().getSubSections();
            ArrayList<String> Return = new ArrayList<>();
            for (FAQSection superSection : superSections) {
                Return.add(superSection.getTopic());
            }
            return Return;
        }
        return null;
    }

    public ArrayList<FAQItem> findItems(String target){
        if(this.topic!=null && this.topic.equals(target)){
            return this.FAQItems;
        }
        ArrayList<FAQItem> Return = null;
        for(FAQSection subsection : this.subSections){
            Return = subsection.findItems(target);
            if(Return != null){
                return Return;
            }
        }
        return Return;
    }

    public String printItems(String target) {
        Collection<FAQItem> Outputs = this.findItems(target);
        String Return = "";
        for(FAQItem Out : Outputs){
            Return += "Question:" + Out.getQuestion() + ";Answer:" + Out.getAnswer()+";\n";
        }
        return Return;
    }

    public ArrayList<FAQSection> getSubSections() {return this.subSections;}
    public ArrayList<FAQItem> getQApairs() {return this.FAQItems;}

//    public String printFAQs() {
//        return null;
//    }
}
