<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 4/13/23
  Time: 8:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- include navbar and meta data, page sources and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/pageTop.jsp"/>

<main class="container about">
    <h1 class="text-center pt-2 my-1">About BargainBuyClub.com</h1>

    <div class="goal">
        <h3 class="mb-3">Our Goal</h3>
        <p>To save you time and money, tracking prices so you don't have to track them yourself and alerting you when
            the
            price has fallen into your buying range.</p>
    </div>

    <div class="process">
        <h3 class="mb-3">Process</h3>

        <p>As easy as 1,2,3...</p>

        <ol>
            <li>Register for our service, which is limited while application is in beta testing.</li>
            <li>Add web site addresses for the products you would like us to track.</li>
            <li>BargainBuyClub tracks the product price regularly throughout the day.</li>
        </ol>

        <p>When the price reaches the desired target, you are sent an email.</p>

    </div>

    <div id="carouselExampleCaptions" class="carousel slide cross-fade" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                    aria-label="Slide 4"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/screenshots/01-home-page.png" class="d-block w-100" alt="home page">
                <div class="carousel-caption d-none d-md-block">
                    <h5>From here...</h5>
                    <p>Just click the <i>Get Started</i> button.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/screenshots/02-login-register.png" class="d-block w-100" alt="login or register">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Getting started...</h5>
                    <p>Choose to login or register.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/screenshots/04-add-alert.png" class="d-block w-100" alt="add alert">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Add alerts...</h5>
                    <p>Add a product url from our list of supported online stores.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/screenshots/05-revised-alerts.png" class="d-block w-100" alt="add alert">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Track your alerts...</h5>
                    <p>Visit as often as you like to add, edit, or delete alerts.</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="false"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="false"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="upcoming">
        <h3 class="my-3 pb-2">Upcoming Changes</h3>

        <ol>
            <li>Users will be able to request stores be added for product tracking.</li>
            <li>Text message functionality will be added as an option for notifications.</li>
            <li>Options to track other online items such as financial instruments will be added...</li>
        </ol>

        <p>Stay tuned...</p>
    </div>
</main>


<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>