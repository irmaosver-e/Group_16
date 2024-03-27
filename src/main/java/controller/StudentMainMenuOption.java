package controller;

public enum StudentMainMenuOption {
    LOGOUT(0),
    CONSULT_FAQ(1),
    SEARCH_PAGES(2),
    CONTACT_STAFF(3);

    public final int value;
    StudentMainMenuOption(int v) {this.value = v;}
}
