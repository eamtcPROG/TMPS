package decorators;

import interfaces.INotificationService;

public class SMSNotificationDecorator extends NotificationServiceDecorator {

    public SMSNotificationDecorator(INotificationService decoratedNotificationService) {
        super(decoratedNotificationService);
    }

    @Override
    public void sendNotification(String recipient, String message) {
        decoratedNotificationService.sendNotification(recipient, message);
        System.out.println("Sending SMS to " + recipient + " with message: " + message);
    }
}
