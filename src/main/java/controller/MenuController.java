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
    }

    public void mainMenu() {
        String role = this.sharedCont.getCurrentUser().getRole(); // Determine the user's role

        switch (role) {
            case "Guest":
                handleGuestMainMenu();
                break;
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

    private boolean handleGuestMainMenu(){
        GuestController guest = new GuestController(this.sharedCont,this.theView,this.authServ,this.emailServ);

        this.theView.displayInfo("Guest Menu:");
        try{
            int index = 0;
            EnumSet<GuestMainMenuOption> menuOptions = EnumSet.allOf(GuestMainMenuOption.class);
            for(GuestMainMenuOption option : menuOptions){
                this.theView.displayInfo("input ["+index+"] for: "+menuOptions);
            }


        }catch (NumberFormatException e){
            this.theView.displayException(e);
        }
        return true;
    }

    private boolean handleStudentMainMenu(){
        this.theView.displayInfo("Student Menu:");
        try{
            int index = 0;
            EnumSet<TeachingStaffMainMenuOption> menuOptions = EnumSet.allOf(TeachingStaffMainMenuOption.class);
            for(TeachingStaffMainMenuOption option : menuOptions){
                this.theView.displayInfo("input ["+index+"] for: "+menuOptions);
            }


        }catch (NumberFormatException e){
            this.theView.displayException(e);
        }
        return true;
    }

    private boolean handleTeachingStaffMainMenu(){
        this.theView.displayInfo("Teaching Staff Menu:");
        int index = 0;
        EnumSet<TeachingStaffMainMenuOption> menuOptions = EnumSet.allOf(TeachingStaffMainMenuOption.class);
        for(TeachingStaffMainMenuOption option : menuOptions){
            this.theView.displayInfo("input ["+index+"] for: "+menuOptions);
        }
        String input = theView.getInput("Please make a selection:");
        try{
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice < menuOptions.size()){
                TeachingStaffMainMenuOption selectedOption =
                        (TeachingStaffMainMenuOption) menuOptions.toArray()[choice];
                switch(choice){
                    case 0:
                        authUserCtrler.logout();
                        break;
                    case 1:
                        teachingStaffCtrler.manageReceivedInquiries(sharedCont.getUnAnsweredInquiries());
                        break;
                }
            }
        }catch (NumberFormatException e){
            this.theView.displayException(e);
        }
        return true;
    }

    private boolean handleAdminStaffMainMenu(){
        this.theView.displayInfo("Admin Staff Menu:");
        int index = 0;
        EnumSet<AdminStaffMainMenuOption> menuOptions = EnumSet.allOf(AdminStaffMainMenuOption.class);
        for(AdminStaffMainMenuOption option : menuOptions){
            this.theView.displayInfo("input ["+index+"] for: "+menuOptions);
        }
        String input = theView.getInput("Please make a selection:");
        try{
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice < menuOptions.size()){
                AdminStaffMainMenuOption selectedOption =
                        (AdminStaffMainMenuOption) menuOptions.toArray()[choice];
                switch(choice){
                    case 0:
                        authUserCtrler.logout();
                        break;
                    case 1:
                        adminStaffCtrler.manageInquiries(sharedCont.getUnAnsweredInquiries());
                        break;
                    case 2:
                        adminStaffCtrler.addPage();
                        break;
                    case 3:
                        adminStaffCtrler.viewAllPages();
                        break;
                    case 4:
                        adminStaffCtrler.manageFAQ();
                        break;
                }
            }



        }catch (NumberFormatException e){
            this.theView.displayException(e);
        }
        return true;
    }

}
