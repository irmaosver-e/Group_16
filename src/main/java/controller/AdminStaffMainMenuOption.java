package controller;

public enum AdminStaffMainMenuOption {
    LOGOUT(0),
    MANAGE_QUERIES(1),
    ADD_PAGE(2),
    MANAGE_FAQ(3);
    public final int value;

    AdminStaffMainMenuOption(int v) {
        this.value = v;
    }

}
