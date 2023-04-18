package com.mdbytes.app.controller.events;

import com.mdbytes.app.model.Alert;

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
    public ArrayList<Alert> loadHome() throws SQLException, IOException {
        ArrayList<Alert> alerts = (ArrayList<Alert>) alertDao.getAll(1);
        return alerts;
    }

    /**
     * Method terminates the active session and returns user to home page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    public boolean goHome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<Alert> homeAlerts = loadHome();
        request.setAttribute("homeAlerts", homeAlerts);
        request.setAttribute("page", "home");
        response.sendRedirect(request.getContextPath());
        return true;


    }
}
