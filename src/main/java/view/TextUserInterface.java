package view;

import model.FAQ;
import model.FAQSection;
import model.Inquiry;
import model.PageSearchResult;
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

    }

    @Override
    public void displayFAQSection(FAQSection faqSection, Boolean includeQuestions) {

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
