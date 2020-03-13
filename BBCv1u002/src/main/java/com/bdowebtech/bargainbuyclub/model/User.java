package com.bdowebtech.bargainbuyclub.model;

public class User {

    private int userID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean isAdmin;

    public User() {
        this.userID = 0;
        this.firstName = null;
        this.lastName = null;
        this.isAdmin = false;
        this.emailAddress = "";
        this.password = "";
    }

    public User(int userID, String firstName, String lastName, String emailAddress, String password, boolean isAdmin) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }
    
    

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "+\n"
                + "userID=" + userID + "\n"
                + "firstName=" + firstName + "\n"
                + ", lastName=" + lastName + "\n"
                + ", emailAddress=" + emailAddress + "\n"
                + ", password=" + password + "\n"
                + ", isAdmin=" + isAdmin + "\n"
                + '}';
    }

    

}
