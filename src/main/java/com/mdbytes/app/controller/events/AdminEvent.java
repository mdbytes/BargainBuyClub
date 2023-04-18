package com.mdbytes.app.controller.events;

import com.mdbytes.app.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminEvent extends Event {

    public AdminEvent(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Method retrieves all system alerts for admin users as requested.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean adminDisplayAlerts(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession() != null && request.getSession().getAttribute("admin-view") == "true") {
                request.getSession().setAttribute("admin-page", "true");
                request.getSession().setAttribute("system-alerts", alertDao.getAll());
                request.setAttribute("page", "admin");
                request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            } else {
                request.setAttribute("errormessage", "Must be logged in as admin to view.");
                request.setAttribute("page", "login");
                request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There is a problem displaying admin alerts");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }

    /**
     * Method gives users administrator privileges as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean makeUserAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getAttribute("user");
            user.setIsAdmin(true);
            userDao.update(user);
            request.getSession().setAttribute("users", userDao.getAll());
            request.setAttribute("page", "admin");
            request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problem making user admin.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }

    /**
     * Method prepares and presents all users when selected by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean displayUsersForAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("users", userDao.getAll());
            request.setAttribute("page", "admin");
            request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problem with user display for admins.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }

    }

    /**
     * Updates latest prices for all products
     *
     * @param request  a servlet http request
     * @param response a servlet http request
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    public void updateSystemPrices(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<Alert> alerts = alertDao.getAll();
        for (Alert alert : alerts) {
            Product product = alert.getProduct();
            ProductScraper scraper = new ProductScraper(product.getProductUrl(), product.getStore().getPriceQuery(), product.getStore().getProductNameQuery());
            Double newPrice = scraper.getProductPrice(product.getProductUrl());
            product.setProductPrice(newPrice);
            productDao.update(product);
        }
        adminDisplayAlerts(request, response);
    }

    /**
     * Sends notifications for current prices below alert prices
     *
     * @param request  a servlet http request
     * @param response a servlet http request
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    public void sendNotifications(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<User> users = userDao.getAll();
        int numberOfEmails = 0;
        for (User user : users) {
            List<Alert> alerts = alertDao.getAll(user.getUserID());
            List<Alert> triggeredAlerts = new ArrayList<>();

            for (Alert alert : alerts) {
                if (alert.getAlertPrice() > alert.getProduct().getProductPrice()) {
                    triggeredAlerts.add(alert);
                }
            }

            if (triggeredAlerts.size() > 0) {
                Notification notification = new Notification(user, triggeredAlerts);
                notification.sendMail();
                numberOfEmails++;
            }
        }

        request.getSession().setAttribute("price-alert-number", numberOfEmails);
        adminDisplayAlerts(request, response);
    }
}
