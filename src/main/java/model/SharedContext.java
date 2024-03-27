package model;

import view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SharedContext {
    public static String ADMIN_STAFF_EMAIL = "jack.tr@hindenburg.ac.uk";
    private Map<String, Collection<String>> faqTopicsUpdateSubscribers;
    private User currentUser;
    private Collection<Page> pages;
    private FAQ faq;
    private Collection<Inquiry> unAnsweredInquires;

    public SharedContext() {
        this.faqTopicsUpdateSubscribers = new HashMap<String, Collection<String>>();
        this.currentUser = new Guest();
        this.pages = new ArrayList<Page>();
        this.faq = new FAQ();
        this.unAnsweredInquires = new ArrayList<Inquiry>();
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
