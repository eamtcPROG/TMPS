package decorators;

import interfaces.INotificationService;

public class EmailNotificationDecorator extends NotificationServiceDecorator {

    public EmailNotificationDecorator(INotificationService decoratedNotificationService) {
        super(decoratedNotificationService);
    }

    @Override
    public void sendNotification(String recipient, String message) {
        decoratedNotificationService.sendNotification(recipient, message);
        System.out.println("Sending email to " + recipient + " with message: " + message);
    }
}
