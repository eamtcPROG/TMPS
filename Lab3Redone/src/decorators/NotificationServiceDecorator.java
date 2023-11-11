package decorators;

import interfaces.INotificationService;

public abstract class NotificationServiceDecorator implements INotificationService {
    protected INotificationService decoratedNotificationService;

    public NotificationServiceDecorator(INotificationService decoratedNotificationService) {
        this.decoratedNotificationService = decoratedNotificationService;
    }
}
