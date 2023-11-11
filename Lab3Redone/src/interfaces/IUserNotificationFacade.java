package interfaces;

import models.User;

public interface IUserNotificationFacade {
    void sendUserNotifications(User user);
}
