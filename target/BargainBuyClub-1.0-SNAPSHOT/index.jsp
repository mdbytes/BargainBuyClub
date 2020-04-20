<%-- 
    Document   : index
    Created on : Feb 28, 2020, 2:46:04 PM
    Author     : Martin Dwyer
--%>
<%@page import="java.util.ArrayList,
                java.text.NumberFormat,
                com.bdowebtech.bargainbuyclub.model.Alert,
                com.bdowebtech.bargainbuyclub.EventHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- include navbar and page sources, styles and scripts --%>
<jsp:include page="/includes/homeTop.html" />

<%-- loading home page alerts --%>
<%
    EventHandler lexi = new EventHandler();
    ArrayList<Alert> alerts = lexi.loadHome();
    NumberFormat cf = NumberFormat.getCurrencyInstance();   
%>


<main class="container-fluid">
    <div class="row">
        <div class="col-lg-6" id="home-left-column">
            <div id="home-jumbo" class="jumbotron" style="text-align:center;">
                <img src="images/shopping-cart.png" id="home-cart-image">
                <h1>BargainBuyClub.com</h1>
                <h5>Filling your cart at the right price</h5>
                <hr>
            </div>

            <div class="center"  id="get-started" >
            <a href="login.jsp"class="btn btn-large btn-success">Get Started</a>
            </div>
            
            <div id="mobile-hr-addition">
                <hr>
            </div>
        </div>
      
            <div class="col-lg-6" id="home-right-column">
                <div class="row">
                    <div id="shopper-home">
                        <img src="images/girl-shopper.png">
                        <h2>How it happens!</h2>
                        <hr>
                    </div>
                    <h5>We let you know when the price is right!</h5>
                </div>
                <p>Tell us the product and the price you want to pay. You can come back to the site to check your alerts. As an example, here are some items we are tracking from 
                    <a href="https://www.bestbuy.com"><img src="images/220px-Best_Buy_Logo.svg.png" alt="Best Buy" class="inline-text-image"></a> online.
                </p>
                <h3>Featured Price Alerts</h3>
                <table class="table" id="home_alerts">
                    <%  if (alerts != null) {

                            for (Alert alert : alerts) {
                                String productName = alert.getProduct().getProductName();
                                if (productName.length() > 75) {
                                    productName = productName.substring(0, 75) + "...";
                                }
                                out.print("<tr><td colspan='3' style='border-top:1px solid black;'>" + productName + "</td></tr>");
                                out.print("<tr><td>Price today " + cf.format(alert.getProduct().getProductPrice()) + "<br></td><td align='center'>Alert price " + cf.format(alert.getAlertPrice()) + "</td><td align='right'><a class='buy-now' href=" + alert.getProduct().getProductUrl() + " target='_blank'>Buy Now</a></td></tr>");
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
    

</main>

<jsp:include page="/includes/homeBottom.html" />
