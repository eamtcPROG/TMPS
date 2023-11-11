package facades;

import interfaces.IUserNotificationFacade;
import decorators.EmailNotificationDecorator;
import decorators.SMSNotificationDecorator;
import interfaces.INotificationService;
import models.User;
import services.NotificationService;

public class UserNotificationFacade implements IUserNotificationFacade {
    private INotificationService emailNotificationService;
    private INotificationService smsNotificationService;

    public UserNotificationFacade() {

        INotificationService basicNotificationService = new NotificationService();
        this.emailNotificationService = new EmailNotificationDecorator(basicNotificationService);
        this.smsNotificationService = new SMSNotificationDecorator(basicNotificationService);
    }

    @Override
    public void sendUserNotifications(User user) {

        if(user.getEmail() != null) {
            emailNotificationService.sendNotification(user.getEmail(), "Welcome");
        }
        if(user.getPhone() != null) {
            smsNotificationService.sendNotification(user.getPhone(), "Welcome");
        }
    }
}
