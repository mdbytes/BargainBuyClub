package com.mdbytes.app.model;

import com.mdbytes.app.config.Env;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.util.List;

public class Notification {

    private final User user;
    private final List<Alert> alerts;
    private final String SENDGRID_API_KEY;

    public Notification(User user, List<Alert> alerts) {
        Env env = new Env();
        this.SENDGRID_API_KEY = env.SENDGRID_API_KEY;
        this.user = user;
        this.alerts = alerts;
    }

    public void sendMail() throws IOException {
        Email from = new Email("martin@mdbytes.com");
        String subject = "BARGAIN BUY ALERT for " + user.getFirstName() + " " + user.getLastName();
        Email to = new Email(user.getEmailAddress());
        String message = "We've got exciting news!  You have a bargain to purchase.  Check it out: \n\n";
        for (Alert alert : alerts) {
            message += "----------------------------------------\n";
            message += "Product: " + alert.getProduct().getProductName() + "\n";
            message += "Your target price:  $" + alert.getAlertPrice() + "\n";
            message += "Recent price:       $" + alert.getProduct().getProductPrice() + "\n";
            message += "----------------------------------------\n";
        }
        message += "\n\nLogin to the app if you would like to review or adjust your alerts:\n\n";
        message += "https://bargain-buy-club.herokuapp.com\n\n";

        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
