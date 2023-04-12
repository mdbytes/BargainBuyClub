package app;

import app.controller.events.HomeEvent;
import app.model.Alert;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        HomeEvent homeEvent = new HomeEvent();
        List<Alert> homeAlerts = homeEvent.loadHome();
        request.setAttribute("homeAlerts", homeAlerts);
        request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
    }
}
