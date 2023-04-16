<%-- 
    Document   : index
    Created on : Feb 28, 2020, 2:46:04 PM
    Author     : Martin Dwyer
    License    : GNU public license in application root folder
--%>
<%@page import="java.util.ArrayList,java.text.NumberFormat" %>
<%@ page import="com.mdbytes.app.model.Alert" %>
<%@ page import="com.mdbytes.app.controller.HomeEvent" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- include navbar and meta data, page sources and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/homeTop.jsp"/>

<%-- loading home page alerts --%>
<%
    ArrayList<Alert> alerts = (ArrayList<Alert>) request.getAttribute("homeAlerts");
    NumberFormat cf = NumberFormat.getCurrencyInstance();
    String errorMessage = "";
    if (request.getSession().getAttribute("errormessage") != null) {
        errorMessage = (String) request.getSession().getAttribute("errormessage");
        request.getSession().setAttribute("errormessage", null);
    }
%>

<!-- HTML for main page begins here -->
<main class="container-fluid">
    <div id="error-message" style="visibility: hidden"><%= errorMessage %>
    </div>

    <!-- Page consists of one row containing two columns  -->
    <div class="row">

        <!-- Column one for site jumbo tron, invitation to get started -->
        <div class="col-lg-6" id="home-left-column">
            <div id="home-jumbo" class="jumbotron" style="text-align:center;">
                <h1>BargainBuyClub.com</h1>
                <img src="images/megaphone.png" id="home page megaphone" class="img-fluid">

                <h5>Filling your cart at the right price</h5>
                <hr>
            </div>

            <!-- A forward to user entry portal when user chooses 'Get Started' -->
            <div class="center" id="get-started">
                <a href="main?action=login-user" class="btn btn-large btn-success">Get Started</a>
            </div>

            <div id="mobile-hr-addition">
                <hr>
            </div>
        </div>

        <!-- Column two provides a brief overview and sample alerts -->
        <div class="col-lg-6" id="home-right-column">
            <div class="row">
                <div id="shopper-home">
                    <img src="images/shopping-cart.png">
                    <h2>How it happens!</h2>
                    <hr>
                </div>
                <h5>We let you know when the price is right!</h5>
            </div>
            <p>Tell us the product and the price you want to pay. You can come back to the site to check your alerts. As
                an example, here are some items we are tracking from
                shop Disney online.
            </p>
            <h3>Featured Price Alerts</h3>

            <!-- Table uses alert data obtained when index.jsp was loaded -->
            <table class="table table-striped" id="home_alerts">
                <% if (alerts != null) {
                    for (Alert alert : alerts) {
                        String productName = alert.getProduct().getProductName();
                        // shorten product name to 75 char for display
                        if (productName.length() > 75) {
                            productName = productName.substring(0, 75) + "...";
                        }
                        out.print("<tr><td colspan='3' style='border-top:1px solid black;'>" + productName + "</td></tr>");
                        out.print("<tr style='border-bottom:1px solid black'><td>Price today " + cf.format(alert.getProduct().getProductPrice()) + "<br></td><td align='center'>Alert price " + cf.format(alert.getAlertPrice()) + "</td><td align='right'><a class='buy-now' href=" + alert.getProduct().getProductUrl() + " target='_blank'>Buy Now</a></td></tr>");
                    }
                } else {
                    out.print("<tr><td colspan='3'><p>No featured alerts at this time</p></td></tr>");
                }
                %>
            </table>
            <p>
                When the online store price reaches your target, an email alert will automatically be sent to you.
            </p>
        </div>
    </div>

    <!-- Button trigger modal -->
    <button id="launch-error-button" type="button" class="btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#errorModal"
            style="visibility: hidden">
        Launch error modal
    </button>

    <!-- Modal -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="errorModalLabel">Oops! Sorry!</h1>
                    <button id="close-modal-btn" type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <%= errorMessage %>
                </div>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
</main>

<%-- include footer document which contains page scripts  --%>
<jsp:include page="/WEB-INF/bbc/includes/homeBottom.html"/>