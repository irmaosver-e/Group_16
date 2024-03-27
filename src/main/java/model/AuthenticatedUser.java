package model;

public class AuthenticatedUser extends User {
    private String email;
    private String role;
    public AuthenticatedUser(String email, String role){}

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
