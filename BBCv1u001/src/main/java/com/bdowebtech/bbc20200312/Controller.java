/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bbc20200312;

import com.bdowebtech.bbc20200312.model.Alert;
import com.bdowebtech.bbc20200312.model.Data.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author marti
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private Database database = new Database();
    
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

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("action"));
        
        switch (request.getParameter("action")) {
//            case "login":
//                String userName = request.getParameter("username");
//                System.err.println("username is " + userName);
//                String password = request.getParameter("password");
//                System.err.println("password is " + password);
//                if (validateUser(userName, password)) {
//                    ArrayList<Alert> userAlerts = getUserAlerts(userName);
//
//                    if (request.getSession() != null) {
//                        request.getSession().invalidate();
//                    }
//                    HttpSession newSession = request.getSession(true);
//                    newSession.setMaxInactiveInterval(300);
//                    newSession.setAttribute("username", userName);
//                    request.setAttribute("useralerts", userAlerts);
//                    request.setAttribute("session", newSession);
//                    System.out.println("Session Id:  " + newSession.getId());
//                    System.out.println("Session user: " + newSession.getAttribute("username").toString());
//                    System.out.println("Session Id:  " + request.getSession().getId());
//                    System.out.println("Session user: " + request.getSession().getAttribute("username").toString());
//                    request.getRequestDispatcher("/displayAlerts.jsp").forward(request, response);
//
//                } else {
//                    String errorMessage = "Username or password invalid";
//                    request.setAttribute("errormessage", errorMessage);
//                    request.getRequestDispatcher("/login.jsp").forward(request, response);
//                }
//                break;
            case "display-alerts":
                ArrayList<Alert> userAlerts = new ArrayList<Alert>();
                String userName = request.getParameter("username");
                System.out.println("Username is : "  + userName);
                userAlerts = database.getUserAlerts(userName);
                request.setAttribute("useralerts",userAlerts);
                request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);    
                break;
            
            case "logout":
                
                
                break;
            case "registerUser":
                // register new user
                // set session
                // display alerts page
                break;
            case "editUser":
                // edit user
                // display users
                break;
            case "addAlert":
                // add new alert
                // display alerts page
                break;
            case "editAlert":
                // edit existing alert
                // display alerts page
                break;
            

            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);

        }

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
