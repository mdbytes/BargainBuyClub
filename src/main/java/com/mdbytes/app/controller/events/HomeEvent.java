package com.mdbytes.app.controller.events;

import com.mdbytes.app.controller.Event;
import com.mdbytes.app.model.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeEvent extends Event {


    public HomeEvent() {
        super();
    }

    /**
     * Method is used to obtain alert objects for the home page illustration.
     *
     * @return alerts
     */
    public ArrayList<Alert> loadHome() {
        ArrayList<Alert> alerts = (ArrayList<Alert>) userDao.getUserAlerts(1);
        return alerts;
    }

    /**
     * Method terminates the active session and returns user to home page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific error occurs
     * @throws IOException      if I/O error occurs
     */
    public void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Alert> homeAlerts = loadHome();
        request.setAttribute("homeAlerts", homeAlerts);
        request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
    }
}
