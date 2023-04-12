<%-- 
    Document   : displayAlerts
    Created on : Feb 28, 2020
    Author     : Martin Dwyer
    License    : GNU public license in application root folder
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- importing necessary Java resources --%>
<%@page import="java.util.Collections,
                java.util.ArrayList,
                java.text.NumberFormat" %>
<%@ page import="app.model.Alert" %>

<%-- including page header including meta data and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/pageTop.jsp"/>

<%
    ArrayList<Alert> alerts = new ArrayList<>();
    String userName = "";
    String sessionID = "";
    String isAdmin = "";
    String adminView = "false";
    NumberFormat cf = NumberFormat.getCurrencyInstance();

    // Checking to see if an active session is in process
    // If not, user is directed back to site home page 
    if (request.getSession().getAttribute("username") != null) {

        // Retrieving current session data
        userName = request.getSession().getAttribute("username").toString();
        isAdmin = request.getSession().getAttribute("admin").toString();
        adminView = request.getSession().getAttribute("admin-view").toString();
        sessionID = request.getSession().getId();
        if (request.getSession().getAttribute("admin-page") == "true") {
            alerts = (ArrayList<Alert>) request.getSession().getAttribute("system-alerts");
        } else {
            alerts = (ArrayList<Alert>) request.getSession().getAttribute("useralerts");
        }

    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<!-- main page HTML content begins here -->
<main id="display-alerts-page" class="container">
    <h1>Displaying Price Alerts
        <h1>
            <h6>User: <%= userName%>
            </h6>
            <br>
            <!-- using session data (if exists) to fill alerts table -->
            <table class="table" id="display-alerts-table">
                <tr>
                    <th>Alert ID</th>
                    <th>Product Name</th>
                    <th>Current Price</th>
                    <th>Alert Price</th>
                    <th></th>
                </tr>
                <%
                    if (alerts != null) {
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

            <!-- actions for users triggered by the following buttons -->
            <!-- button for adding alerts for this user -->
            <div class="user-button">
                <form class="button-form" id="add-alert-" action="Controller" method="get">
                    <input class="hidden-element " type="text" value="add-alert-page" name="action">
                    <input id="add-alert-button" type="submit" value="   Add Alert  "
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>

            <!-- button to display edit alert form -->
            <div class="user-button">
                <form class="button-form" id="edit-alert">
                    <input id="display-edit-alert" type="button" value="  Edit Alert  "
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>

            <!-- button to display delete alert form -->
            <div class="user-button">
                <form class="button-form" id="delete-alert">
                    <input id="display-delete-alert" type="button" value="Delete Alert"
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>

            <!-- the following div appears only if admin options set to "true" in session -->
            <div id="admin-options">
                <div id="is-admin"><%= isAdmin%>
                </div>

                <!-- displays alerts for all users in system for admin users -->
                <div class="user-button">
                    <form class="button-form" id="admin-display-alerts" action="Controller" method="get">
                        <input class="hidden-element " type="text" value="admin-display-alerts" name="action">
                        <input id="admin-display-alerts-button" type="submit" value="Display All"
                               class="btn btn-primary btn-large btn-success user-button">
                    </form>
                </div>

                <!-- forwards admin user to a page displaying all system users -->
                <div class="user-button">
                    <form class="button-form" id="admin-display-users" action="Controller" method="get">
                        <input class="hidden-element " type="text" value="admin-display-users" name="action">
                        <input id="admin-display-users-button" type="submit" value="Display Users"
                               class="btn btn-primary btn-large btn-success user-button">
                    </form>
                </div>
            </div>

            <!-- sign out user ending session and returning to home page -->
            <div class="user-button">
                <form class="button-form" id="sign-out" action="Controller" method="get">
                    <input class="hidden-element " type="text" value="logout" name="action">
                    <input id="sign-out-button" type="submit" value="   Sign Out   "
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>

            <!-- displays when edit alert button clicked -->
            <div id="edit-alert-button-div">
                <form class="button-form" id="edit-alert-form" action="Controller" method="get">
                    <input class="hidden-element " type="text" value="edit-alert" name="action">
                    <label for="alert-id-input">Alert ID for editing:</label>
                    <select name="alert-id" class="form-control" id="alert-id-input">
                        <%
                            if (alerts != null) {
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
                    <label for="alert-price-input">New alert price:</label>
                    <input type="text" name="alert-price" id="alert-price-input"><br/>
                    <input id="edit-alert-button" type="submit" value="   Submit   "
                           class="btn btn-primary btn-large btn-success">
                </form>
            </div>

            <!-- displays when delete alert button clicked -->
            <div id="delete-alert-button-div">
                <form class="button-form" id="delete-alert-form" action="Controller" method="get">
                    <input class="hidden-element " type="text" value="delete-alert" name="action">
                    <label for="alert-id-input">Alert ID to be deleted:</label>
                    <select name="alert-id" class="form-control" id="alert-id-input">
                        <%
                            if (alerts != null) {
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
                    <input id="delete-alert-button" type="submit" value="   Submit   "
                           class="btn btn-primary btn-large btn-success">
                </form>
            </div>
            <span class="error-message" style="clear:both;"></span>

</main>

<%-- include pageBottom for footer and scripts --%>
<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>