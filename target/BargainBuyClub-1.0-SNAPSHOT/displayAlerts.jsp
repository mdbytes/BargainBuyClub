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
    String username = "", sessionID = "";
    
    if(request.getSession().getAttribute("username") != null) {
                username = request.getSession().getAttribute("username").toString();
	    	sessionID = request.getSession().getId().toString();
    
    
    } else {
    
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

    
    ArrayList<Alert> alerts = new ArrayList<Alert>();
    if (request.getAttribute("useralerts") != null) {
        alerts = (ArrayList<Alert>) request.getAttribute("useralerts");

    }


%>
<body>
    <h1>Displaying Price Alerts @ BargainBuyClub.com</h1>
    <h4>User: <%= username%></h4>
    <br>

    <table class="table">
        <tr>
            <th>Product Name</th>
            <th>Current Price</th>
            <th>Alert Price</th>
            <th>User Email</th>
        </tr>
        <%                for (Alert alert : alerts) {
                out.print("<tr>");
                out.print("<td>");
                out.print(alert.product.productName);
                out.print("</td>");
                out.print("<td>");
                out.print(alert.product.productPrice);
                out.print("</td>");
                out.print("<td>");
                out.print(alert.alertPrice);
                out.print("</td>");
                out.print("<td>");
                out.print(alert.getUser().emailAddress);
                out.print("</td>");
                out.print("</tr>");
            }
        %>
    </table>


</table>
<br>
Username:  <%= username %><br>
Session:  <<%= sessionID %><br>
</body>
<jsp:include page="/includes/pageTop.html" />