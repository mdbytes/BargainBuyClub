<%-- 
    Document   : displayAlerts
    Created on : Feb 28, 2020, 2:49:43 PM
    Author     : marti
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bdowebtech.bargainbuyclub.model.Alert" %>
<jsp:include page="/includes/pageTop.html" />
<jsp:include page="/includes/timeOutCode.html" />
<%
    ArrayList<Alert> alerts = new ArrayList<Alert>();
    String userName = "";
    String sessionID = "";
    String isAdmin = "";
    String adminView = "false";
    
    NumberFormat cf = NumberFormat.getCurrencyInstance();
    if (request.getSession() != null) {
        
        userName = request.getSession().getAttribute("username").toString();
        isAdmin = request.getSession().getAttribute("admin").toString();
        adminView = request.getSession().getAttribute("admin-view").toString();
        sessionID = request.getSession().getId();
        
        if(adminView.equals("false")) {
            alerts = (ArrayList<Alert>) request.getSession().getAttribute("useralerts");
        } else {
            alerts = (ArrayList<Alert>) request.getSession().getAttribute("system-alerts");
        }
        
    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<div id="display-alerts-page" class="container">
    <h1>Displaying Price Alerts<h1>
    <h6>User: <%= userName%></h6>
    <br>

    <table class="table" id="display-alerts-table">
        <tr>
            <th>Alert ID</th>
            <th>Product Name</th>
            <th>Current Price</th>
            <th>Alert Price</th>
            <th></th>
        </tr>
        <%
            if(alerts != null) {
            for (Alert alert : alerts) {
                out.print("<tr>");
                out.print("<td>");
                out.print(alert.getAlertID());
                out.print("</td>");
                out.print("<td>");
                out.print(alert.getProduct().getProductName());
                out.print("</td>");
                out.print("<td align='right'>");
                out.print(cf.format(alert.getProduct().getProductPrice()));
                out.print("</td>");
                out.print("<td align='right'>");
                out.print(cf.format(alert.getAlertPrice()));
                out.print("</td>");
                out.print("<td align='right'><a class='buy-now' href=");
                out.print(alert.getProduct().getProductUrl());
                out.print(" target='_blank'>Buy Now</a></td>");
                out.print("</tr>");
            }
            } else {
                out.print("<tr><td>No alerts at this time</td></tr>");
            }
        %>
    </table>

    <br>

    
    

    <div class="user-button">
        <form class="button-form" id="add-alert-" action="Controller" method="get">
            <input class="hidden-element " type="text" value="add-alert-page" name="action">
            <input id="add-alert-button" type="submit" value="   Add Alert  " class="btn btn-primary btn-large btn-success user-button">
        </form>      
    </div>

    <div class="user-button">

        <form class="button-form" id="edit-alert">
            <input class="hidden-element " type="text" value="edit-alert" name="action">
            <input class="hidden-element " type="text" name="alert-selected">
            <input id="display-edit-alert" type="button" value="  Edit Alert  " class="btn btn-primary btn-large btn-success user-button">
        </form>            
    </div>

    <div class="user-button">
        <form class="button-form" id="delete-alert">
            <input class="hidden-element " type="text" value="delete-alert" name="action">
            <input class="hidden-element " type="text" name="alert-selected">
            <input id="display-delete-alert" type="button" value="Delete Alert" class="btn btn-primary btn-large btn-success user-button">
        </form>            
    </div>

    <div id="admin-options">
        <div id="is-admin"><%= isAdmin %></div>
        <div class="user-button">
            <form class="button-form" id="admin-display-alerts" action="Controller" method="get">
                <input class="hidden-element " type="text" value="admin-display-alerts" name="action">
                <input id="admin-display-alerts-button" type="submit" value="Display All" class="btn btn-primary btn-large btn-success user-button">
            </form>      
        </div>
        <div class="user-button">
            <form class="button-form" id="admin-display-users" action="Controller" method="get">
                <input class="hidden-element " type="text" value="admin-display-users" name="action">
                <input id="admin-display-users-button" type="submit" value="Display Users" class="btn btn-primary btn-large btn-success user-button">
            </form>      
        </div>
    </div>

    <div class="user-button">
        <form class="button-form" id="sign-out" action="Controller" method="get">
            <input class="hidden-element " type="text" value="logout" name="action">
            <input id="sign-out-button" type="submit" value="   Sign Out   " class="btn btn-primary btn-large btn-success user-button">
        </form>
    </div>

    <div id="edit-alert-button-div">
        <form class="button-form" id="sign-out" action="Controller" method="get">
            <input class="hidden-element " type="text" value="edit-alert" name="action">
            <label for="alert-id">Alert ID for editing:</label>
            <select name="alert-id" class="form-control" id="alert-id-input">
                <%
                    if(alerts != null) {
                    for (Alert alert : alerts) {
                        out.print("<option value='");
                        out.print(alert.getAlertID());
                        out.print("'>");
                        out.print(alert.getAlertID());
                        out.print("</option>");
                    }
                    } else {
                        out.print("<tr><td>No alerts at this time</td></tr>");
                    }
                %>
            </select><br/>
            <label for="alert-price">New alert price:</label>
            <input type="text" name="alert-price" id="alert-price-input"><br/>
            <input id="edit-alert-button" type="submit" value="   Submit   " class="btn btn-primary btn-large btn-success">
        </form>
    </div>
    
    <div id="delete-alert-button-div">
        <form class="button-form" id="sign-out" action="Controller" method="get">
            <input class="hidden-element " type="text" value="delete-alert" name="action">
            <label for="alert-id">Alert ID to be deleted:</label>
            <select name="alert-id" class="form-control" id="alert-id-input">
                <%
                    if(alerts != null) {
                    for (Alert alert : alerts) {
                        out.print("<option value='");
                        out.print(alert.getAlertID());
                        out.print("'>");
                        out.print(alert.getAlertID());
                        out.print("</option>");
                    }
                    } else {
                        out.print("<tr><td>No alerts at this time</td></tr>");
                    }
                %>
            </select><br/>
            <input id="delete-alert-button" type="submit" value="   Submit   " class="btn btn-primary btn-large btn-success">
        </form>
    </div>

    <span class="error-message" style="clear:both;"></span>


</div>


<jsp:include page="/includes/pageBottom.html" />