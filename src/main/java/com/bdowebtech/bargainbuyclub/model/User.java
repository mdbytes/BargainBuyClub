package com.bdowebtech.bargainbuyclub.model;

import java.util.ArrayList;

public class User {

    public String firstName;
    public String lastName;
    public String emailAddress;
    public String password;
    public boolean isAdmin;

    public User() {
        this.firstName = null;
        this.lastName = null;
        this.isAdmin = false;
        this.emailAddress = null;
        this.password = null;
        
    }
    
    

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }



        @Override
        public String toString
        
            () {
        return "User{"
                    + "firstName='" + firstName + '\''
                    + ", lastName='" + lastName + '\''
                    + ", emailAddress='" + emailAddress + '\''
                    + ", password='" + password + '\''
                    + '}';
        }

    }
