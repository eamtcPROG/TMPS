import controllers.UserController;
import facades.UserNotificationFacade;
import interfaces.IController;
import interfaces.IService;
import interfaces.IUserNotificationFacade;
import models.User;
import proxies.UserServiceProxy;
import services.LoggingService;
import services.UserService;
import utils.LoggingServiceAdapter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password access: ");
        String password = scanner.nextLine();
        boolean isAuthorized = "admin".equals(password);

        LoggingService loggingService = new LoggingService();
        IService<User> userService = UserService.getInstance();

        IService<User> loggingAdapter = new LoggingServiceAdapter(loggingService,userService);

        IService<User> userServiceWithAccessControl = new UserServiceProxy(loggingAdapter, isAuthorized);
        IUserNotificationFacade userNotificationFacade = new UserNotificationFacade();
        IController userController = UserController.getInstance(userServiceWithAccessControl, userNotificationFacade);

        userController.create();
        userController.display();
    }
}