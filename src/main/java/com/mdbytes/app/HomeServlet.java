package com.mdbytes.app;

import com.mdbytes.app.controller.HomeEvent;
import com.mdbytes.app.model.Alert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
        List<Alert> homeAlerts = null;
        try {
            homeAlerts = homeEvent.loadHome();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("homeAlerts", homeAlerts);
        request.setAttribute("page", "home");
        request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
    }
}
