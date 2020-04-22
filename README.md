# BargainBuyClub
 A Java full-stack application designed to track prices of products sold in online stores.  When prices reach a target, the user is automatically emailed an alert from the system.  The application involves user registration, login, and the ability to set up new alerts.  
 
 The application was built using the NetBeans 11.3 IDE and has the following features: 
 
 1.  It is built with a MVC framework.  The web sources provide the view, the controller can be found in the Controller and EventHandler servlets, and the model can be found in the Java source packages.  
 
 2.  The view has many opportunities for input validation, most of which is accomplished with HTML validation.  However, user validation is completed with the help of connecting to the application database.  
 
 3.  The application is built using JavaScript and jQuery in addition to the Bootstrap framework.  
 
 4.  Special JavaScript features include the ability to reveal forms for editing and deleting alerts or users, as well as a JavaScript timer that warns the user after 8 minutes of inactivity and logs them off after 10 minutes of inactivity.  
 
 5.  The model uses the JSOUP API to parse product page html content in order to retrieve the product price and name from the URL alone.  
 
 6.  At the present time, the application is designed to work with BestBuy as the only store.  In the model, the Store class could be easily adapted to add more stores.  
 
 7.  In addition to license information, folders are provided containing system use case, flow chart, mysql database and javadoc comments.  

8.  Screenshots of the application running are also provided in the root folder.   
