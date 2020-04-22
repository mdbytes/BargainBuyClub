/*
 * File:        EventHandler.java
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 * Date:        April 20, 2020
 */
package com.bdowebtech.bargainbuyclub;

import com.bdowebtech.bargainbuyclub.model.User;
import com.bdowebtech.bargainbuyclub.model.Product;
import com.bdowebtech.bargainbuyclub.model.Alert;
import com.bdowebtech.bargainbuyclub.model.Data.HomePage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * EventHandler class executes actions requested by users.
 * 
 * @author Martin Dwyer
 */
public class EventHandler {

    // Attributes for the user
    private User user;
    private int userID;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    // Attributes for alerts
    private Alert alert;
    private int alertID;
    private Product product;
    private int storeID;
    private String productUrl;
    private double alertPrice;

    // Attributes for session
    private String sessionID;

    /**
     *   Default constructor
     */
    public EventHandler() {
        this.user = null;
        this.userID = 0;
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.alert = null;
        this.alertID = 0;
        this.product = null;
        this.storeID = 0;
        this.productUrl = "";
        this.alertPrice = 0.0;
        this.sessionID = "";
    }
    
    /**
     * Method is used to obtain alert objects for the home page illustration.
     * 
     * @return alerts
     */
    public ArrayList<Alert> loadHome() {
        HomePage frontPage = new HomePage();   
        return frontPage.getAlerts();
    }

    /**
     * Method terminates the active session and returns user to home page.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific error occurs
     * @throws IOException if I/O error occurs
     */
    public void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Method processes login username and password request and forwards users to 
     * their individual alerts page after user validation. 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        userName = request.getParameter("username");
        password = request.getParameter("password");
        if (User.validateUser(userName, password)) {
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(300);
            newSession.setAttribute("admin", User.getUserByEmailAddress(userName).isIsAdmin());
            newSession.setAttribute("username", userName);
            request.setAttribute("session", newSession);
            newSession.setAttribute("useralerts", User.getUserAlerts(userName));
            newSession.setAttribute("admin-view", "false");
            request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("sign-in-error-message", "Username or password incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    /**
     * Method logs out user and returns the user to the home page.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     */
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/Controller?action=home");
    }

    /**
     * Method used to forward validated user to their display alerts page. 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     */
    public void displayAlerts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            userName = request.getSession().getAttribute("username").toString();
            password = request.getSession().getAttribute("password").toString();
            request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Username or password incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    /**
     * Method retrieves all system alerts for admin users as requested.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     */
    public void adminDisplayAlerts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().setAttribute("admin-view", "true");
            request.getSession().setAttribute("system-alerts", Alert.getAllAlerts());
            request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Must be logged in as admin to view.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    /**
     * Method processes a registration request and forwards a new user to the display
     * alerts page unless email is already registered.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        userName = request.getParameter("username");
        password = request.getParameter("password");
        firstName = request.getParameter("first-name");
        lastName = request.getParameter("last-name");
        System.out.println(User.getUserByEmailAddress(userName).getUserID());
        if (User.getUserByEmailAddress(lastName).getUserID() == 0) {
            User user = new User();
            user = User.addUser(firstName, lastName, userName, password, false);
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(300);
            newSession.setAttribute("admin", User.getUserByEmailAddress(userName).isIsAdmin());
            newSession.setAttribute("username", userName);
            request.setAttribute("session", newSession);
            newSession.setAttribute("useralerts", User.getUserAlerts(userName));
            newSession.setAttribute("admin-view", "false");
            request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Email address already exists in database");
            System.out.println(request.getAttribute("sign-up-error-message").toString());
            request.getRequestDispatcher("registerUser.jsp").forward(request, response);
        }
    }

    /**
     * Method forwards current user to addAlert page for entering new alert information.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void displayNewAlertPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession thisSession = (HttpSession) request.getSession();
        if (thisSession != null) {
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Must be logged in to add alerts.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    /**
     * Method processes information submitted for new alert.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void addAlert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        userName = request.getSession().getAttribute("username").toString();
        storeID = Integer.parseInt(request.getParameter("store"));
        alertPrice = Double.parseDouble(request.getParameter("alert-price"));
        productUrl = request.getParameter("product_url");
        product = Product.addProduct(storeID, productUrl);
        alert = Alert.addAlert(product.getProductID(), User.getUserByEmailAddress(userName).getUserID(), alertPrice);
        request.getSession().setAttribute("useralerts", User.getUserAlerts(userName));
        request.getSession().setAttribute("system-alerts", Alert.getAllAlerts());
        request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
    }

    /**
     * Method edits alert information as requested by users.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void editAlert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        userName = request.getSession().getAttribute("username").toString();
        alertID = Integer.parseInt(request.getParameter("alert-id"));
        alertPrice = Double.parseDouble(request.getParameter("alert-price"));
        Alert.updateAlertPrice(alertID, alertPrice);
        request.getSession().setAttribute("useralerts", User.getUserAlerts(userName));
        request.getSession().setAttribute("system-alerts", Alert.getAllAlerts());
        request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
    }

    /**
     * Method deletes alerts as requested by the user.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void deleteAlert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        userName = request.getSession().getAttribute("username").toString();
        alertID = Integer.parseInt(request.getParameter("alert-id"));
        Alert.deleteAlert(alertID);
        request.getSession().setAttribute("useralerts", User.getUserAlerts(userName));
        request.getSession().setAttribute("system-alerts", Alert.getAllAlerts());
        request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
    }

    /**
     * Method prepares and presents all users when selected by validated administrators. 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void displayUsersForAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.getSession().setAttribute("users", User.getAllUsers());
        request.getRequestDispatcher("displayUsers.jsp").forward(request, response);
    }

    /**
     * Method edits user information as requested by validated administrators.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        userID = Integer.parseInt(request.getParameter("user-id"));
        String newEmailAddress = request.getParameter("new-email-address");
        String newPassword = request.getParameter("new-password");
        if (!newEmailAddress.equals("")) {
            User.updateUserEmailAddress(userID, newEmailAddress);
        }
        if (!newPassword.equals("")) {
            User.updateUserPassword(userID, newPassword);
        }
        request.getSession().setAttribute("users", User.getAllUsers());
        request.getRequestDispatcher("displayUsers.jsp").forward(request, response);
    }

    /**
     * Method deletes users as requested by validated administrators.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        userID = Integer.parseInt(request.getParameter("user-id"));
        User.deleteUser(userID);
        request.getSession().setAttribute("users", User.getAllUsers());
        request.getRequestDispatcher("displayUsers.jsp").forward(request, response);
    }

    /**
     * Method gives users administrator privileges as requested by validated administrators.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException if I/O error occurs
     * @throws SQLException if database error occurs
     */
    public void makeUserAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        userID = Integer.parseInt(request.getParameter("user-id"));
        User.makeUserAdmin(userID);
        request.getSession().setAttribute("users", User.getAllUsers());
        request.getRequestDispatcher("displayUsers.jsp").forward(request, response);
    }
}
