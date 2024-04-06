package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FAQ {
    private FAQSection faqSection;

    public FAQ(){
        this.faqSection = new FAQSection();
    }

    public FAQSection getFaqSection() {
        return this.faqSection;
    }
}
