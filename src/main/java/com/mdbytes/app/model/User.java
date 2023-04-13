/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public
 *              License, a free copyleft license for software.  A copy of this
 *              license has been provided in the root folder of this application.
 */
package com.mdbytes.app.model;

/**
 * User class provides the structure for users of the system.
 *
 * @author Martin Dwyer
 */
public class User {

    private final int userID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean isAdmin;

    /**
     * Default constructor creates a null object.
     */
    public User() {
        this.userID = 0;
        this.firstName = null;
        this.lastName = null;
        this.isAdmin = false;
        this.emailAddress = "";
        this.password = "";
    }

    /**
     * Complete constructor specifies every attribute of User objects.
     *
     * @param userID       a unique identifier, an integer
     * @param firstName    the user's first name, a String
     * @param lastName     the user's last name, a String
     * @param emailAddress the user's email address, a String
     * @param password     the user's entered password, a String
     * @param isAdmin      true is user is administrator, false otherwise
     */
    public User(int userID, String firstName, String lastName, String emailAddress, String password, boolean isAdmin) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Method returns User object first name.
     *
     * @return first name, a String
     */
    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method returns User object last name.
     *
     * @return last name, a String
     */
    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method returns User object email address.
     *
     * @return email address, a String
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Method returns password stored in memory for User object during registration.
     *
     * @return password, a String
     */
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method returns the User object's unique ID.
     *
     * @return ID, an integer
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Method returns the administrative status of User objects.
     *
     * @return true if administrator, false otherwise
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    private void setIsAdmin(boolean isAdmin) {
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
