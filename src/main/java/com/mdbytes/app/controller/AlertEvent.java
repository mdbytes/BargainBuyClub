package com.mdbytes.app.controller;

import com.mdbytes.app.model.Alert;
import com.mdbytes.app.model.Product;
import com.mdbytes.app.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AlertEvent extends Event {

    public AlertEvent() {
        super();
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
            int storeID = Integer.parseInt(request.getParameter("store"));
            double alertPrice = Double.parseDouble(request.getParameter("alert-price"));
            String productUrl = request.getParameter("product_url");
            Product product = productDao.addProduct(storeID, productUrl);
            Alert alert = alertDao.addAlert(product.getProductID(), userDao.getUserByEmailAddress(user.getEmailAddress()).getUserID(), alertPrice);
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

    /**
     * Method retrieves all system alerts for admin users as requested.
     *
     * @param request  servlet request
     * @param response servlet response
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
}
