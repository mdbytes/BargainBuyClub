# BargainBuyClub

A full-stack web application designed to track prices of products sold online. When prices reach a target, the user is
automatically emailed an alert from the system. The application involves user registration, login, and the ability to
set up new alerts.

The application was originally built using Python with Flask web support. However, over the past three years development
shifted to from Python to Java EE and now Java Spring Data REST with React client.

Today's edition is built with Maven support and deployed to a cloud server with a Docker container.

<ol>
<li style="margin-bottom: 8px">It is built with a Java Spring Data REST server and a React framework client.</li>
<li style="margin-bottom: 8px">Data is stored in a MySQL database on a cloud server, accessed through Spring Data REST repositories.</li>
<li style="margin-bottom: 8px">User inputs are validated at the server and client levels. </li>
<li style="margin-bottom: 8px">The application is built using JavaScript and Bootstrap frameworks.</li>
<li style="margin-bottom: 8px">Special JavaScript features include the ability to reveal forms for editing and deleting alerts or users.</li>
<li style="margin-bottom: 8px">This demo uses the JSOUP API to parse product page html content in order to retrieve the product price and name from the URL alone. Future releases could easily adapt store API's to retrieve current price data.</li>
<li style="margin-bottom: 8px">Email notifications are sent to users via the SendGrid email API.</li>
<li style="margin-bottom: 8px">Screenshots of the application running are also provided in the root folder.</li>
<li style="margin-bottom: 8px">Diagrams used in the development of this application can be found in the diagrams folder of the root directory.</li>
<li style="margin-bottom: 8px">The application is deployment using docker container and NGINX proxy server for security.</li>

</ol>

Current development is focused on:

<ol>
<li style="margin-bottom: 8px">Expanding the number of stores supported utilizing store API's as available (e.g. Amazon).</li>
</ol>
