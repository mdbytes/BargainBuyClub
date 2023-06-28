package com.mdbytes.app.rest.service;

import com.mdbytes.app.entity.Alert;
import com.mdbytes.app.entity.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
class NotificationService {
    private SendGrid sendGrid;

    @Autowired
    public NotificationService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendMail(User user, List<Alert> alerts) throws IOException {
        Email from = new Email("martin@mdbytes.com");
        String subject = "BARGAIN BUY ALERT for " + user.getFirstName() + " " + user.getLastName();
        Email to = new Email(user.getEmail());
        String message = "We've got exciting news!  You have a bargain to purchase.  Check it out: \n\n";
        for (Alert alert : alerts) {
            message += "----------------------------------------\n";
            message += "Product: " + alert.getProduct().getProductName() + "\n";
            message += "Your target price:  $" + alert.getAlertPrice() + "\n";
            message += "Recent price:       $" + alert.getProduct().getRecentPrice() + "\n";
            message += "----------------------------------------\n";
        }
        message += "\n\nLogin to the app if you would like to review or adjust your alerts:\n\n";
        message += "https://bargain-buy-club.herokuapp.com\n\n";

        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

}
