/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bargainbuyclub;

import com.bdowebtech.bargainbuyclub.model.Alert;
import com.bdowebtech.bargainbuyclub.model.Data.Database;
import com.bdowebtech.bargainbuyclub.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marti
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        

        
            switch(request.getParameter("action")) {
                case "login":
                    Database database = new Database();
                    database.setUpDatabase();
                    String userName = request.getParameter("username");
                    System.err.println("username is " + userName);
                    String password = request.getParameter("password");
                    System.err.println("password is " + password);
                    if(database.validateUser(userName, password)) {
                            ArrayList<Alert> userAlerts = database.getUserAlerts(userName);
                            
                            if(request.getSession() != null) request.getSession().invalidate();
                            HttpSession newSession = request.getSession(true);
                            newSession.setMaxInactiveInterval(300);
                            newSession.setAttribute("username", userName);
                            request.setAttribute("useralerts",userAlerts);
                            request.setAttribute("session", newSession);
                            System.out.println("Session Id:  " + newSession.getId());
                            System.out.println("Session user: " + newSession.getAttribute("username").toString());
                            System.out.println("Session Id:  " + request.getSession().getId());
                            System.out.println("Session user: " + request.getSession().getAttribute("username").toString());
                            request.getRequestDispatcher("/displayAlerts.jsp").forward(request, response);
                        
                    } else {
                        request.setAttribute("errorMessage","Username or password invalid");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                    break;
                case "register":
                    // register new user
                    // set session
                    // display alerts page
                    break;
                case "newalert":
                    // add new alert
                    // display alerts page
                    break;
                case "editalert":
                    // edit existing alert
                    // display alerts page
                    break;
                case "displayusers":
                    // display list of users
                    break;
                case "edituser":
                    // edit user
                    // display users
                    // break
                default:
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    
                
            }
            
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Concierge</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
//            for(User user: database.users) {
//                out.println("<p>" + user.toString() + "</p>");
//            }
//            out.println("</body>");
//            out.println("</html>");
//        }
                
       
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
