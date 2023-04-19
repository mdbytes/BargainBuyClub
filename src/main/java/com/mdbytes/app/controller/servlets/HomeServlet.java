package com.mdbytes.app.controller.servlets;

import com.mdbytes.app.controller.events.HomeEvent;
import com.mdbytes.app.model.Alert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet handles all requests from the root path, forwarding them to the application
 * home page.  All further application processing is performed through the controller.
 */
@WebServlet(name = "HomeServlet", value = "")
public class HomeServlet extends HttpServlet {
    /**
     * Method forwards request and response to the application home page.
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs.
     * @throws IOException      if occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeEvent homeEvent = new HomeEvent(request, response);
        List<Alert> homeAlerts = new ArrayList<>();
        try {
            if (request.getAttribute("homeAlerts") != null) {
                homeAlerts = (ArrayList<Alert>) request.getAttribute("homeAlerts");
            } else {
                homeAlerts = homeEvent.loadHome();
                request.setAttribute("homeAlerts", homeAlerts);
            }
            request.setAttribute("page", "home");
            request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Trouble in HomeServlet");
            request.setAttribute("errormessage", "Cannot retrieve alerts at this time.");
            request.setAttribute("page", "home");
            request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
        }
    }
}
