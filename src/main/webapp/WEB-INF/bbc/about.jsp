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


</main>


<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>