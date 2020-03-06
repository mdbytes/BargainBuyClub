<%-- 
    Document   : displayAlerts
    Created on : Feb 28, 2020, 2:49:43 PM
    Author     : marti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bdowebtech.bargainbuyclub.model.Data.Database" %>
<%@page import="com.bdowebtech.bargainbuyclub.model.ProductAlerts" %>
<jsp:include page="/includes/pageTop.html" />
    <%
        Database database = new Database();
        if ((Database) request.getAttribute("database") != null) {
            database = (Database) request.getAttribute("database");
        }


    %>
    <body>
        <h1>BargainBuyClub.com</h1>
        <h3>Display Alerts</h3>
        <br>

        <table class="table">
            <tr>
                <th>Product Name</th>
                <th>Current Price</th>
                <th>Alert Price</th>
                <th>User Email</th>
            </tr>
            <%                for (ProductAlerts alert : database.alerts) {
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(alert.productName);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(alert.productPrice);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(alert.alertPrice);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(alert.userEmail);
                    out.print("</td>");
                    out.print("</tr>");
                }
            %>
        </table>


    </table>

</body>
<jsp:include page="/includes/pageTop.html" />