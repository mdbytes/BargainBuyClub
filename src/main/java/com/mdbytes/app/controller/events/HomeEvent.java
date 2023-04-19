package com.mdbytes.app.controller.events;

import com.mdbytes.app.model.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeEvent extends Event {


    public HomeEvent(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Method is used to obtain alert objects for the home page illustration.
     *
     * @return alerts
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    public ArrayList<Alert> loadHome() {
        try {
            ArrayList<Alert> alerts = (ArrayList<Alert>) alertDao.getAll(1);
            return alerts;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Trouble getting home alerts");
            return null;
        }
    }

    /**
     * Method terminates the active session and returns user to home page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Alert> homeAlerts = null;
            if (request.getAttribute("homeAlerts") != null) {
                homeAlerts = (ArrayList<Alert>) request.getAttribute("homeAlerts");
            } else {
                homeAlerts = loadHome();
                request.setAttribute("homeAlerts", homeAlerts);
            }
            request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

