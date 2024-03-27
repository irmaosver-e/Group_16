package controller;

public enum GuestMainMenuOption {
    LOGIN(0),
    CONSULT_FAQ(1),
    SEARCH_PAGES(2),
    CONTACT_STAFF(3);
    public final int value;
    GuestMainMenuOption (int v) {this.value = v;}


}
