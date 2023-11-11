import commands.CommandHistory;
import commands.CommandInvoker;
import commands.CreateUserCommand;
import commands.DisplayUsersCommand;
import controllers.UserController;
import interfaces.IControllerUser;
import interfaces.IService;
import interfaces.Command;
import models.User;
import observers.EmailNotificationObserver;
import observers.LoggerObserver;
import observers.UserEventManager;
import services.UserService;
import strategies.EmailValidationStrategy;
import strategies.PhoneValidationStrategy;


public class Main {
    public static void main(String[] args) {

        IService<User> userService = UserService.getInstance();
        UserEventManager eventManager = new UserEventManager();
        LoggerObserver loggerObserver = new LoggerObserver();
        EmailNotificationObserver emailObserver = new EmailNotificationObserver("admin@mail.com");

        eventManager.attach(loggerObserver);
        eventManager.attach(emailObserver);
        IControllerUser userController = UserController.getInstance(userService, new EmailValidationStrategy(),eventManager);


        CommandHistory history = new CommandHistory();
        CommandInvoker invoker = new CommandInvoker(history);

        Command createUserCommand = new CreateUserCommand(userController);
        Command displayUsersCommand = new DisplayUsersCommand(userController);

        invoker.setCommand(createUserCommand);
        invoker.executeCommand();

        userController.setValidationStrategy(new PhoneValidationStrategy());

        invoker.setCommand(createUserCommand);
        invoker.executeCommand();

        invoker.setCommand(displayUsersCommand);
        invoker.executeCommand();

        eventManager.detach(emailObserver);
        eventManager.detach(loggerObserver);

        history.displayHistory();

    }
}