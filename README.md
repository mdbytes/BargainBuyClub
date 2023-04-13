# BargainBuyClub

A Java web application designed to track prices of products sold online. When prices reach a target, the user is
automatically emailed an alert from the system. The application involves user registration, login, and the ability to
set up new alerts.

The application was originally built using Python with Flask web support. However, over the past three years development
shifted to the Java EE web application framework. Today's edition is built as a Maven web application with Jakarta EE
support for Servlets and JSP.

<ol>
<li style="margin-bottom: 8px">It is built with a MVC framework. The web sources provide the view (JSP Pages), with the controller and model located in their own appropriately named source packages.</li>
<li style="margin-bottom: 8px">Data is stored in a MySQL database and accessed through various data access objects.  These can be found in the dao package.</li>
<li style="margin-bottom: 8px">The view has many opportunities for input validation, which is accomplished with HTML validation. User identity validation, authorization levels, and other inputs are validated at the server level as well. </li>
<li style="margin-bottom: 8px">The application is built using JavaScript and jQuery in addition to the Bootstrap framework.</li>
<li style="margin-bottom: 8px">Special JavaScript features include the ability to reveal forms for editing and deleting alerts or users, as well as
   a JavaScript timer that warns the user after 8 minutes of inactivity and logs them off after 10 minutes of
   inactivity.</li>
<li style="margin-bottom: 8px">The model uses the JSOUP API to parse product page html content in order to retrieve the product price and name from
   the URL alone.</li>
<li style="margin-bottom: 8px">Screenshots of the application running are also provided in the root folder.</li>
<li style="margin-bottom: 8px">Source comments exist in files as well as in the generated javadocs - found in the javadoc folder of the root directory.</li>
<li style="margin-bottom: 8px">Diagrams used in the development of this application can be found in the diagrams folder of the root directory.</li>
</ol>

Current development is focused on:

<ol>
<li style="margin-bottom: 8px">Expanding the number of stores supported.</li>
<li style="margin-bottom: 8px">Transitioning to web scraping tools less sensitive to modern JavaScript frameworks.</li>
<li style="margin-bottom: 8px">Deployment using docker container and NGINX proxy server for security.</li>
</ol>

One of my favorite features of Java, the Javadocs, for this project can be reviewed at the following
address: https://bargain-buy-club.herokuapp.com/javadoc/

The use case, flow chart, and UML class diagrams can be viewed at: https://bargain-buy-club.herokuapp.com/diagrams/
