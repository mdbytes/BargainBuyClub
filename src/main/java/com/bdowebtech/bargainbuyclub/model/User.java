/*
 * Author: Martin Dwyer
 * Date: April 17, 2020
 * Description: This file is part of the BargainBuyClub application written by developer Martin Dwyer.
 */
package com.bdowebtech.bargainbuyclub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;

public class User {

    private int userID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean isAdmin;
    private static Database db = new Database();

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
    
    public static void makeUserAdmin(int userID) {
        User user = new User();
        String query = "UPDATE users SET is_admin = 'true' "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
        
    }
    
    public static boolean validateUser(String username, String userPassword) {
        boolean isValid = false;
        User user = getUserByEmailAddress(username);
        if (user.getPassword().equals(DigestUtils.sha256Hex(userPassword))) {
            isValid = true;
        }
        return isValid;
    }
    
    public static void updateUserEmailAddress(int userID,String newEmailAddress) {
        int count = 0;
        String query = "UPDATE users "
                + "SET email_address = '" + newEmailAddress + "' "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
    }
    
    public static void updateUserPassword(int userID,String newPassword) {
        String query = "UPDATE users "
                + "SET password = '" + DigestUtils.sha256Hex(newPassword) + "' "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
    }
    
    public static void deleteUser(int userID) {
        String query = "DELETE FROM users "
                + "WHERE user_id = " + userID + ";";
        db.executeUpdate(query);
        
    }

}
