package com.mdbytes.app.controller;

import com.mdbytes.app.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class AlertEvent extends Event {

    public AlertEvent(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Method forwards current user to addAlert page for entering new alert information.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    public boolean displayNewAlertPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession thisSession = request.getSession();
            if (thisSession != null) {
                request.setAttribute("page", "alerts");
                request.getRequestDispatcher("WEB-INF/bbc/addAlert.jsp").forward(request, response);
            } else {
                request.setAttribute("page", "login");
                request.setAttribute("errormessage", "Must be logged in to add alerts.");
                request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem displaying new alerts");
            handleException(request, response, "There was a problem displaying new alerts");
            return false;
        }
    }

    /**
     * Method processes information submitted for new alert.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    public boolean addAlert(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            Store store = storeDao.get(Integer.parseInt(request.getParameter("store")));
            double alertPrice = Double.parseDouble(request.getParameter("alert-price"));
            String productUrl = request.getParameter("product_url");
            ProductScraper scraper = new ProductScraper(productUrl, store.getPriceQuery(), store.getProductNameQuery());
            String productName = scraper.getProductName(productUrl);
            Double productPrice = scraper.getProductPrice(productUrl);
            Date date = Date.valueOf(LocalDate.now());
            Product product = new Product(productUrl, store, productName, productPrice, date);
            Product savedProduct = productDao.add(product);
            Alert alert = alertDao.addAlert(savedProduct.getProductID(), userDao.getUserByEmailAddress(user.getEmailAddress()).getUserID(), alertPrice);
            request.getSession().setAttribute("useralerts", userDao.getUserAlerts(user.getUserID()));
            request.getSession().setAttribute("system-alerts", alertDao.getAll());
            request.setAttribute("page", "alerts");
            request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem adding that alert.");
            handleException(request, response, "There was a problem adding that alert.");
            return false;
        }

    }

    /**
     * Method edits alert information as requested by users.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    public boolean editAlert(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            int alertID = Integer.parseInt(request.getParameter("alert-id"));
            double alertPrice = Double.parseDouble(request.getParameter("alert-price"));
            System.out.println(request.getSession().getAttribute("user-id"));
            alertDao.updateAlertPrice(alertID, alertPrice);
            request.getSession().setAttribute("useralerts", userDao.getUserAlerts(user.getUserID()));
            request.getSession().setAttribute("system-alerts", alertDao.getAll());
            request.setAttribute("page", "alerts");
            request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem editing that alert");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }

    }

    /**
     * Method deletes alerts as requested by the user.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    public boolean deleteAlert(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            int alertID = Integer.parseInt(request.getParameter("alert-id"));
            alertDao.delete(alertID);
            request.getSession().setAttribute("useralerts", userDao.getUserAlerts(user.getUserID()));
            request.getSession().setAttribute("system-alerts", alertDao.getAll());
            request.setAttribute("page", "alerts");
            request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem deleting that alert.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }


    /**
     * Method used to forward validated user to their display alerts page.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    public boolean displayAlerts(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession() != null) {
                User user = (User) request.getSession().getAttribute("user");
                request.setAttribute("useralerts", userDao.getUserAlerts(user.getUserID()));
                request.getSession().setAttribute("admin-page", "false");
                request.setAttribute("page", "alerts");
                request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            } else {
                request.setAttribute("errormessage", "Username or password incorrect");
                request.setAttribute("page", "login");
                request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There seems to be a problem displaying alerts at this time.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }

    }


}
