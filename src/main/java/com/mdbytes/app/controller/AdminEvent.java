package com.mdbytes.app.controller;

import com.mdbytes.app.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminEvent extends Event {

    public AdminEvent(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
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

    /**
     * Method gives users administrator privileges as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    public boolean makeUserAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getAttribute("user");
            userDao.makeUserAdmin(user.getUserID());
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


    public void updateSystemPrices(HttpServletRequest request, HttpServletResponse response) {


    }
}