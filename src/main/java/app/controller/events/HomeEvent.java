package app.controller.events;

import app.controller.Event;
import app.model.Alert;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
