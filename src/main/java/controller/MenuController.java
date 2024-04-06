package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Guest;
import model.SharedContext;
import view.View;

import java.util.EnumSet;

public class MenuController extends Controller {
    private AuthenticatedUserController authUserCtrler;
    private GuestController guestCtrler ;
    private StaffController staffCtrler;
    private InquirerController inquirerCtrler;
    private AdminStaffController adminStaffCtrler;
    private TeachingStaffController teachingStaffCtrler;

    public MenuController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
        authUserCtrler = new AuthenticatedUserController(sharedCont, theView, authServ, emailServ);
        guestCtrler = new GuestController(sharedCont, theView, authServ, emailServ);
        staffCtrler = new StaffController(sharedCont, theView, authServ, emailServ);
        inquirerCtrler = new InquirerController(sharedCont, theView, authServ, emailServ);
        adminStaffCtrler = new AdminStaffController(sharedCont, theView, authServ, emailServ);
        teachingStaffCtrler = new TeachingStaffController(sharedCont, theView, authServ, emailServ);

    }

    public void mainMenu() {

        if(sharedCont.getCurrentUser() instanceof Guest)
        {
            handleGuestMainMenu();
        }
        if(!(sharedCont.getCurrentUser() instanceof Guest)) {
            String role = this.sharedCont.getCurrentUser().getRole(); // Determine the user's role
            switch (role) {
                case "Student":
                    handleStudentMainMenu();
                    break;
                case "TeachingStaff":
                    handleTeachingStaffMainMenu();
                    break;
                case "AdminStaff":
                    handleAdminStaffMainMenu();
                    break;
                default:
                    this.theView.displayError("Unknown user role.");
                    break;
            }
        }
    }

    private boolean handleGuestMainMenu(){
        this.theView.displayInfo("Guest Menu:");
        int index = 0;
        EnumSet<GuestMainMenuOption> menuOptions = EnumSet.allOf(GuestMainMenuOption.class);
        for (GuestMainMenuOption option : menuOptions) {
            this.theView.displayInfo("input [" + index + "] for: " + option.toString().replace("_", " "));
            index++;
        }
        String input = theView.getInput("Please make a selection: ");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice < menuOptions.size()) {
                GuestMainMenuOption selectedOption = (GuestMainMenuOption) menuOptions.toArray()[choice];

                switch (selectedOption) {
                    case LOGIN:
                        guestCtrler.login();
                        break;
                    case CONSULT_FAQ:
                        inquirerCtrler.consultFAQ();
                        break;
                    case SEARCH_PAGES:
                        inquirerCtrler.searchPages();
                        break;
                    case CONTACT_STAFF:
                        inquirerCtrler.contactStaff();
                        break;
                    default:
                        theView.displayError("Unknown selection.");
                        break;
                }
            } else {
                theView.displayError("Invalid selection. Please try again.");
            }
        } catch (NumberFormatException e) {
            this.theView.displayError("Invalid input. Please enter a number.");
        }
        return true;
    }

    private boolean handleStudentMainMenu(){
        this.theView.displayInfo("Student Menu:");
        int index = 0;
        EnumSet<StudentMainMenuOption> menuOptions = EnumSet.allOf(StudentMainMenuOption.class);
        for (StudentMainMenuOption option : menuOptions) {
            this.theView.displayInfo("input [" + index + "] for: " + option.toString().replace("_", " "));
            index++;
        }
        String input = theView.getInput("Please make a selection: ");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice < menuOptions.size()) {
                StudentMainMenuOption selectedOption = (StudentMainMenuOption) menuOptions.toArray()[choice];
                switch (selectedOption) {
                    case LOGOUT:
                        authUserCtrler.logout();
                        // Redirect to the main menu page by calling the mainMenu() method of MenuController - assumption for logout logic
                        mainMenu();
                        break;
                    case CONSULT_FAQ:
                        inquirerCtrler.consultFAQ();
                        break;
                    case SEARCH_PAGES:
                        inquirerCtrler.searchPages();
                        break;
                    case CONTACT_STAFF:
                        inquirerCtrler.contactStaff();
                        break;
                    default:
                        theView.displayError("Unknown selection.");
                        break;
                }
            } else {
                theView.displayError("Invalid selection. Please try again.");
            }
        } catch (NumberFormatException e) {
            this.theView.displayError("Invalid input. Please enter a number.");
        }
        return true;
    }

    private boolean handleTeachingStaffMainMenu(){
        this.theView.displayInfo("Teaching Staff Menu:");
        int index = 0;
        EnumSet<TeachingStaffMainMenuOption> menuOptions = EnumSet.allOf(TeachingStaffMainMenuOption.class);
        for (TeachingStaffMainMenuOption option : menuOptions) {
            this.theView.displayInfo("input [" + index + "] for: " + option.toString().replace("_", " "));
            index++;
        }
        String input = theView.getInput("Please make a selection: ");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice < menuOptions.size()) {
                TeachingStaffMainMenuOption selectedOption = (TeachingStaffMainMenuOption) menuOptions.toArray()[choice];
                switch (selectedOption) {
                    case LOGOUT:
                        authUserCtrler.logout();
                        break;
                    case MANAGE_RECEIVED_QUERIES:
                        teachingStaffCtrler.manageReceivedInquiries();
                        break;
                    default:
                        theView.displayError("Unknown selection.");
                        break;
                }
            } else {
                theView.displayError("Invalid selection. Please try again.");
            }
        } catch (NumberFormatException e) {
            this.theView.displayError("Invalid input. Please enter a number.");
        }
        return true;
    }

    private boolean handleAdminStaffMainMenu(){
        this.theView.displayInfo("Admin Staff Menu:");
        int index = 0;
        EnumSet<AdminStaffMainMenuOption> menuOptions = EnumSet.allOf(AdminStaffMainMenuOption.class);
        for (AdminStaffMainMenuOption option : menuOptions) {
            this.theView.displayInfo("input [" + index + "] for: " + option.toString().replace("_", " "));
            index++;
        }
        String input = theView.getInput("Please make a selection: ");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice < menuOptions.size()) {
                AdminStaffMainMenuOption selectedOption = (AdminStaffMainMenuOption) menuOptions.toArray()[choice];
                switch (selectedOption) {
                    case LOGOUT:
                        authUserCtrler.logout();
                        break;
                    case MANAGE_QUERIES:
                        adminStaffCtrler.manageInquiries();
                        break;
                    case ADD_PAGE:
                        adminStaffCtrler.addPage();
                        break;
                    case SEE_ALL_PAGES:
                        adminStaffCtrler.viewAllPages();
                        break;
                    case MANAGE_FAQ:
                        adminStaffCtrler.manageFAQ();
                        break;
                    default:
                        theView.displayError("Unknown selection.");
                        break;
                }
            } else {
                theView.displayError("Invalid selection. Please try again.");
            }
        } catch (NumberFormatException e) {
            this.theView.displayError("Invalid input. Please enter a number.");
        }
        return true;
    }

}
