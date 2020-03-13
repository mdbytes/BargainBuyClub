<%-- 
    Document   : index
    Created on : Feb 28, 2020, 2:46:04 PM
    Author     : marti
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bdowebtech.bargainbuyclub.model.Data.HomePageData" %>
<%@page import="com.bdowebtech.bargainbuyclub.model.Alert" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/includes/pageTop.html" />
<%
        HomePageData frontPage = new HomePageData();
        frontPage.setUpFrontPage();
       
%>
<body class="container-fluid">
    <div class="row">
        <div id="home_jumbo" class="col-lg jumbotron">
            <div id="home_jumbo_content">
                <img src="images/shopping-cart.png">
                <h1 class="logo-font">BargainBuyClub.com</h1>
                <p class="lead">Helping you get the best price for all of your online shopping needs.</p>
                <hr class="my-4"/>
                <p>Here's how it works:</p>
                <p>You tell us the products you want to track, and the price you want to pay.</p>
                <p>We scan online stores and alert you when the price drops below your target price.</p>
                <p>For a limited time during beta testing, membership is free.</p>
                <a class="btn btn-primary btn-large btn-success" href="#">Sign Up</a>
                <br><br>
                <p>Already a member? Sign in below.</p>
                <a class="btn btn-primary btn-large btn-success" href="login.jsp">Sign In</a>
            </div>
        </div>
        <div id="home_alerts" class="col-lg jumbotron">
            <h2 class="logo-font">Set the price you want to pay!</h2>
            <p>Tell us the product and the price you want to pay. We will track online stores and send you an email when
                your bargain is ready. Once items are set up, prices are tracked, and logged in users can see their
                items, the latest price for the item on the website, and the price at which the user will received an
                alert. Users also receive a link on each report to go and purchase the item.</p>
            <p>To illustrate, here are some items we are tracking from <a href="https://www.bestbuy.com"><img src="images/220px-Best_Buy_Logo.svg.png" alt="Best Buy"></a> online.
            </p>
            <table>
                <tr>
                    <th colspan="3">Featured Price Alerts</th>
                </tr>
                <%  if (frontPage.alerts != null) {
                        for (Alert alert : frontPage.alerts) {
                            out.print("<tr><td colspan='3'><h6>" + alert.getProduct().getProductName() + "</h6></td></tr>");
                            out.print("<tr><td>Price today " + alert.getProduct().getProductPrice() + "<br></td><td>Alert price " + alert.getAlertPrice() + "</td><td><a href=" + alert.getProduct().getProductUrl() + " target='_blank'>Buy Now</a></td></tr>");

                        }
                    } else {
                        out.print("<tr><td colspan='3'><p>No featured alerts at this time</p></td></tr>");
                    }


                %>
            </table>

        </div>
    </div>
</body>
<jsp:include page="/includes/pageBottom.html" />
