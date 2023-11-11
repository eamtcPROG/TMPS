package services;

import interfaces.INotificationService;

public class NotificationService implements INotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending notification to " + recipient + ": " + message);
    }
}

