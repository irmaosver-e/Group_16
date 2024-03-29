package view;

import model.FAQ;
import model.FAQSection;
import model.Inquiry;
import model.PageSearchResult;

import java.util.ArrayList;
import java.util.Collection;

public interface View {
    public String getInput(String input);
    public boolean getYesNoInput(String input);
    public void displayInfo(String info);
    public void displaySuccess(String success);
    public void displayFailure(String fail);
    public void displayWarning(String warning);
    public void displayError(String error);
    public void displayException(Exception e);
    public void displayDivider();
    public void displayFAQ(FAQ faq, Boolean includeSections);
    public void displayFAQSection(FAQSection faqSection, Boolean includeQuestions);
    public void displayInquiry(Inquiry inquiry);
    public void displaySearchResults(Collection<PageSearchResult> results);


}

