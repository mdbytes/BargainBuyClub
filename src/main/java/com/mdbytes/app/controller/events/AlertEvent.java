package com.mdbytes.app.controller.events;

import com.mdbytes.app.controller.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AlertEvent extends Event {

    public AlertEvent() {
        super();
    }

    /**
     * Method forwards current user to addAlert page for entering new alert information.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     * @throws SQLException     if database error occurs
     */
    public void displayNewAlertPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession thisSession = request.getSession();
        if (thisSession != null) {
            request.getRequestDispatcher("WEB-INF/bbc/addAlert.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Must be logged in to add alerts.");
            request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
        }

    }

    /**
     * Method processes information submitted for new alert.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     * @throws SQLException     if database error occurs
     */
    public void addAlert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        userName = request.getSession().getAttribute("username").toString();
        storeID = Integer.parseInt(request.getParameter("store"));
        alertPrice = Double.parseDouble(request.getParameter("alert-price"));
        productUrl = request.getParameter("product_url");
        product = productDao.addProduct(storeID, productUrl);
        alert = alertDao.addAlert(product.getProductID(), userDao.getUserByEmailAddress(userName).getUserID(), alertPrice);
        request.getSession().setAttribute("useralerts", userDao.getUserAlerts((int) request.getSession().getAttribute("user-id")));
        request.getSession().setAttribute("system-alerts", alertDao.getAll());
        request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
    }

    /**
     * Method edits alert information as requested by users.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     * @throws SQLException     if database error occurs
     */
    public void editAlert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        userName = request.getSession().getAttribute("username").toString();
        alertID = Integer.parseInt(request.getParameter("alert-id"));
        alertPrice = Double.parseDouble(request.getParameter("alert-price"));
        System.out.println(request.getSession().getAttribute("user-id"));
        alertDao.updateAlertPrice(alertID, alertPrice);
        request.getSession().setAttribute("useralerts", userDao.getUserAlerts((int) request.getSession().getAttribute("user-id")));
        request.getSession().setAttribute("system-alerts", alertDao.getAll());
        request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
    }

    /**
     * Method deletes alerts as requested by the user.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     * @throws SQLException     if database error occurs
     */
    public void deleteAlert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        userName = request.getSession().getAttribute("username").toString();
        alertID = Integer.parseInt(request.getParameter("alert-id"));
        userID = (int) request.getSession().getAttribute("user-id");
        alertDao.delete(alertID);
        request.getSession().setAttribute("useralerts", userDao.getUserAlerts(userID));
        request.getSession().setAttribute("system-alerts", alertDao.getAll());
        request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
    }


    /**
     * Method used to forward validated user to their display alerts page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void displayAlerts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            userID = (int) request.getSession().getAttribute("user-id");
            request.setAttribute("useralerts", userDao.getUserAlerts(userID));
            request.getSession().setAttribute("admin-page", "false");
            request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Username or password incorrect");
            request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
        }
    }

    /**
     * Method retrieves all system alerts for admin users as requested.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void adminDisplayAlerts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null && request.getSession().getAttribute("admin-view") == "true") {
            request.getSession().setAttribute("admin-page", "true");
            request.getSession().setAttribute("system-alerts", alertDao.getAll());
            request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Must be logged in as admin to view.");
            request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
        }
    }

}
