package com.mdbytes.app.rest.service;

import com.mdbytes.app.entity.Alert;
import com.mdbytes.app.entity.User;
import com.mdbytes.app.repository.AlertRepository;
import com.mdbytes.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private AlertRepository alertRepository;

    private UserRepository userRepository;

    private NotificationService notificationService;

    public AlertService() {
    }

    @Autowired
    public AlertService(AlertRepository alertRepository,
                        UserRepository userRepository,
                        NotificationService notificationService) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public Integer sendPriceAlerts() throws IOException {
        List<User> users = userRepository.findAll();
        int numberOfEmails = 0;
        for (User user : users) {
            List<Alert> alerts = alertRepository.findByUser(user);
            List<Alert> triggeredAlerts = new ArrayList<>();

            for (Alert alert : alerts) {

                System.out.println(alert);
                System.out.println(alert.getProduct());
                if (alert.getAlertPrice() > alert.getProduct().getRecentPrice()) {
                    triggeredAlerts.add(alert);
                }
            }

            if (triggeredAlerts.size() > 0) {
                notificationService.sendMail(user, triggeredAlerts);
                numberOfEmails++;
            }
        }
        return numberOfEmails;
    }

}

