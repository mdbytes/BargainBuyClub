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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bdowebtech.bargainbuyclub.model.Data.Database" %>
<%@page import="com.bdowebtech.bargainbuyclub.model.Alert" %>
<jsp:include page="/includes/pageTop.html" />
<%
    ArrayList<Alert> alerts = new ArrayList<Alert>();
    String userName = "";
    String sessionID = "";

    if (request.getAttribute("session") != null) {
        userName = request.getParameter("username");
        HttpSession thisSession = (HttpSession) request.getAttribute("session");
        sessionID = thisSession.getId();
        if (request.getAttribute("useralerts") != null) {
            alerts = (ArrayList<Alert>) request.getAttribute("useralerts");
        }
    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
<body>
    <div class="container">
        <h1>Displaying Price Alerts @ BargainBuyClub.com</h1>
        <h4>User: <%= userName%></h4>
        <br>

        <table class="table">
            <tr>
                <th>Product Name</th>
                <th>Current Price</th>
                <th>Alert Price</th>
                <th>User Email</th>
            </tr>
            <%
                for (Alert alert : alerts) {
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(alert.getProduct().getProductName());
                    out.print("</td>");
                    out.print("<td>");
                    out.print(alert.getProduct().getProductPrice());
                    out.print("</td>");
                    out.print("<td>");
                    out.print(alert.getAlertPrice());
                    out.print("</td>");
                    out.print("<td>");
                    out.print(alert.getUser().getEmailAddress());
                    out.print("</td>");
                    out.print("</tr>");
                }
            %>
        </table>

        <br>



        <form action="Controller" method="get">
            <input class="hidden-element " type="text" value="add-alert" name="action">
            <input type="submit" value="Add Alert" class="btn btn-primary btn-large btn-success">
        </form>

        <form action="Controller" method="get">
            <input class="hidden-element " type="text" value="edit-alert" name="action">
            <input type="submit" value="Edit Alert" class="btn btn-primary btn-large btn-success">
        </form>            

        <form action="Controller" method="get">
            <input class="hidden-element " type="text" value="delete-alert" name="action">
            <input type="submit" value="Delete Alert" class="btn btn-primary btn-large btn-success">
        </form>            

        <form action="Controller" method="get">
            <input class="hidden-element " type="text" value="logout" name="action">
            <input type="submit" value="Sign Out" class="btn btn-primary btn-large btn-success">
        </form>     

    </div>
</body>
<jsp:include page="/includes/pageTop.html" />