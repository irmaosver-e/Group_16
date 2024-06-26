package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SharedContext {
    public static String ADMIN_STAFF_EMAIL = "jack.tr@hindenburg.ac.uk";
    private Map<String, Collection<String>> faqTopicsUpdateSubscribers;
    private User currentUser;
    private Map<String, Page> pages;
    private FAQ faq;
    public Collection<Inquiry> unAnsweredInquiries;

    public SharedContext() {
        this.faqTopicsUpdateSubscribers = new HashMap<String, Collection<String>>();
        this.currentUser = new Guest();
        this.pages = new HashMap<>();
        this.faq = new FAQ();
        this.unAnsweredInquiries = new ArrayList<Inquiry>();
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public FAQ getFaq() {
        return this.faq;
    }
    public boolean unregisterForFAQUpdates(String email, String topic){
        return this.faqTopicsUpdateSubscribers.get(topic).remove(email);
    }

    public boolean registerForFAQUpdates(String email, String topic) {
        if(this.faqTopicsUpdateSubscribers.get(topic) == null){
            ArrayList<String> Subscribers = new ArrayList<>();
            Subscribers.add(email);
            this.faqTopicsUpdateSubscribers.put(topic, Subscribers);
        }else {
            this.faqTopicsUpdateSubscribers.get(topic).add(email);
        }

        return true;
    }

    public Collection<String> usersSubscribedTopic(String topic){
        return this.faqTopicsUpdateSubscribers.get(topic);
    }

    public Map<String, Collection<String>> getFaqTopicsUpdateSubscribers() {
        return faqTopicsUpdateSubscribers;
    }
    public void setCurrentUser(User currentUser) {this.currentUser = currentUser;}

    public void addPage(Page newPage){this.pages.put(newPage.getTitle(), newPage);}
    public Map<String,Page> getPages(){return this.pages;}

    public Collection<Inquiry> getUnAnsweredInquiries(){return unAnsweredInquiries;}


}
