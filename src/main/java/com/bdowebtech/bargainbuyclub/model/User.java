/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * User class provides the structure for users of the system. 
 * 
 * @author Martin Dwyer
 */
public class User {

    private int userID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean isAdmin;
    private static Database db = new Database();

    /**
     *  Default constructor creates a null object.
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
     * @param userID a unique identifier, an integer
     * @param firstName the user's first name, a String
     * @param lastName the user's last name, a String
     * @param emailAddress the user's email address, a String
     * @param password the user's entered password, a String
     * @param isAdmin true is user is administrator, false otherwise
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

    /**
     * Method returns the User object's unique ID.
     * 
     * @return ID, an integer
     */
    public int getUserID() {
        return userID;
    }

    private void setPassword(String password) {
        this.password = password;
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

    /**
     * Method adds User object to application database. 
     * 
     * @param firstName the user's first name, a String
     * @param lastName the user's last name, a String
     * @param emailAddress the user's email address, a String
     * @param userPassword the user's password, a String
     * @param isAdmin true if user is administrator, false otherwise
     * 
     * @return user, a User object
     */
    public static User addUser(String firstName, String lastName, String emailAddress, String userPassword, boolean isAdmin) {
        if (getUserByEmailAddress(emailAddress).getUserID() == 0) {
            User user = new User();
            String query = "INSERT INTO users "
                    + "(first_name,last_name,email_address,password,is_admin) "
                    + "VALUES ("
                    + "'" + firstName + "',"
                    + "'" + lastName + "',"
                    + "'" + emailAddress + "',"
                    + "'" + DigestUtils.sha256Hex(userPassword) + "',"
                    + "'" + isAdmin + "')";
            db.executeUpdate(query);
            user = getUserByEmailAddress(emailAddress);
            return user;
        } else {
            return getUserByEmailAddress(emailAddress);
        }
    }

    /**
     * Method returns all alerts registered by a given user.
     * 
     * @param emailAddress the user's email address
     * 
     * @return alerts, a list of Alert objects
     * 
     * @throws SQLException when database error occurs
     */
    public static ArrayList<Alert> getUserAlerts(String emailAddress) throws SQLException {
        User user = getUserByEmailAddress(emailAddress);
        int userID = user.getUserID();
        ArrayList<Alert> userAlerts = new ArrayList<Alert>();
        String query = "SELECT * FROM alerts "
                + "WHERE user_id = '" + userID + "';";

            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                Alert alert = new Alert(
                        (int) rs.getObject("alert_id"),
                        getUserByID((int) rs.getObject("user_id")),
                        Product.getProductByID((int) rs.getObject("product_id")),
                        (double) rs.getObject("alert_price")
                );
                userAlerts.add(alert);
            }

        return userAlerts;
    }
    
    /**
     * Method returns a list of all users registered in the application database. 
     * 
     * @return users, a list of User objects 
     */
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList();
        String query = "SELECT * FROM users;";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                int userID = (int) rs.getObject("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userName = rs.getString("email_address");
                String passWord = rs.getString("password");
                boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
                users.add(new User(userID, firstName, lastName, userName, passWord, isAdmin));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
       
    /**
     * Method to retrieve user from database with email address. 
     * 
     * @param emailAddress the user's email address
     * 
     * @return user, a User object
     */
    public static User getUserByEmailAddress(String emailAddress) {
        User user = new User();
        String query = "SELECT * FROM users "
                + "WHERE email_address = '" + emailAddress + "';";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                int userID = (int) rs.getObject("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userName = rs.getString("email_address");
                String passWord = rs.getString("password");
                boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
                user = new User(userID, firstName, lastName, userName, passWord, isAdmin);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    /**
     * Method to retrieve User object from database with user ID.
     * 
     * @param userID a unique identifier for each user, an integer
     * 
     * @return user, a User object
     */
    public static User getUserByID(int userID) {
        User user = new User();
        String query = "SELECT * FROM users "
                + "WHERE user_id = " + userID + ";";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userName = rs.getString("email_address");
                String passWord = rs.getString("password");
                boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
                user = new User(userID, firstName, lastName, userName, passWord, isAdmin);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    /**
     * Method to make an existing user an administrator.
     * 
     * @param userID a unique identifier for each user, an integer
     */
    public static void makeUserAdmin(int userID) {
        User user = new User();
        String query = "UPDATE users SET is_admin = 'true' "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
        
    }
    
    /**
     * Method to validate user credentials against those stored in database. 
     * 
     * @param username the user's email address
     * @param userPassword the user's entered password
     * 
     * @return true if valid credentials given, false otherwise
     */
    public static boolean validateUser(String username, String userPassword) {
        boolean isValid = false;
        User user = getUserByEmailAddress(username);
        if (user.getPassword().equals(DigestUtils.sha256Hex(userPassword))) {
            isValid = true;
        }
        return isValid;
    }
    
    /**
     * Method for updating existing user's email address. 
     * 
     * @param userID a unique identifier for each user, an integer
     * @param newEmailAddress a valid email address
     */
    public static void updateUserEmailAddress(int userID,String newEmailAddress) {
        int count = 0;
        String query = "UPDATE users "
                + "SET email_address = '" + newEmailAddress + "' "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
    }
    
    /**
     * Method for updating existing user's password.
     * 
     * @param userID a unique identifier for each user, an integer
     * @param newPassword a valid password
     */
    public static void updateUserPassword(int userID,String newPassword) {
        String query = "UPDATE users "
                + "SET password = '" + DigestUtils.sha256Hex(newPassword) + "' "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
    }
    
    /**
     * Method to delete existing user from the database. 
     * 
     * @param userID a unique identifier for each user, an integer
     */
    public static void deleteUser(int userID) {
        String query = "DELETE FROM users "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
        
    }

}
