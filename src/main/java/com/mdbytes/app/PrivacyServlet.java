package com.mdbytes.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet handles all requests from the privacy path, forwarding them to the application
 * privacy page.
 */
@WebServlet(name = "PrivacyServlet", value = "/privacy")
public class PrivacyServlet extends HttpServlet {
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
        request.setAttribute("page", "privacy");
        request.getRequestDispatcher("WEB-INF/bbc/privacy.jsp").forward(request, response);
    }
}