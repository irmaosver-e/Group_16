package view;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TextUserInterface implements View{
    private  Scanner scanner = new Scanner(System.in);
    @Override
    public String getInput(String input) {
        System.out.println(input);
        return scanner.nextLine();
    }

    @Override
    public boolean getYesNoInput(String input) {
        return getInput(input).equalsIgnoreCase("yes");
    }

    public void displayInfo(String text) {
        System.out.println(text);
    }

    @Override
    public void displaySuccess(String success) {
        displayInfo(success);
    }

    @Override
    public void displayFailure(String fail) {
        displayInfo(fail);
    }

    @Override
    public void displayWarning(String warning) {
        displayInfo(warning);
    }

    @Override
    public void displayError(String error) {
        displayInfo(error);
    }

    @Override
    public void displayException(Exception e) {
        displayInfo(e.getMessage());
    }

    @Override
    public void displayDivider() {

    }

    @Override
    public void displayFAQ(FAQ faq, Boolean includeSections) {
        ArrayList<FAQSection> upperTopics= faq.getFaqSection().getSubSections();
        int count = 0;
        while (count<upperTopics.size()) {
            System.out.println(count + ") " + upperTopics.get(count).getTopic());
            count=count+1;
        }
    }

    @Override
    public void displayFAQSection(FAQSection faqSection, Boolean includeQuestions) {
        System.out.println("Section: " + faqSection.getTopic());
        ArrayList<FAQItem> items = faqSection.getFAQItems();
        ArrayList<FAQSection> subsections = faqSection.getSubSections();
        int count = 0;
        while (count<items.size()){
            System.out.println("Question: " + items.get(count).getQuestion() + "\nAnswer: " + items.get(count).getAnswer());
            count=count+1;
        }
        int secCount = 0;
        while (secCount<subsections.size()){
            System.out.println(secCount + ") Subsection: " + subsections.get(secCount).getTopic());
            secCount = secCount+1;
        }
    }

    @Override
    public void displaySearchResults(Collection<PageSearchResult> results) {
        for(PageSearchResult result : results)
        {
            displayInfo(result.getFormattedContent()+"\n");
        }
    }

    @Override
    public void displayInquiry(Inquiry inquiry){
        displayInfo("Sender: " + inquiry.getInquirerEmail());
        displayInfo("Subject: " + inquiry.getSubject());
        displayInfo("Content: " + inquiry.getEmailContent());
    }

}
