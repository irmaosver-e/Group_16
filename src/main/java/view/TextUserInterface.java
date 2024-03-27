package view;

import model.FAQ;
import model.FAQSection;
import model.PageSearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TextUserInterface implements View{
    private  Scanner scanner;
    @Override
    public String getInput(String input) {
        System.out.println(input);
        return scanner.nextLine();
    }

    @Override
    public boolean getYesNoInput(String input) {
        return false;
    }

    public void displayInfo(String text) {
        System.out.println(text);
    }

    @Override
    public void displaySuccess(String success) {

    }

    @Override
    public void displayFailure(String fail) {

    }

    @Override
    public void displayWarning(String warning) {

    }

    @Override
    public void displayError(String error) {

    }

    @Override
    public void displayException(Exception e) {

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

    }

}
