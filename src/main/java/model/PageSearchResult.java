package model;

public class PageSearchResult {
    private String formattedContent;

    public PageSearchResult(String rawResult){this.formattedContent = rawResult;}

    public String getFormattedContent(){return formattedContent;}
}
