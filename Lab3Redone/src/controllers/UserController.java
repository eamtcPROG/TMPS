package controllers;

import interfaces.IController;
import interfaces.INotificationService;
import interfaces.IService;
import interfaces.IUserNotificationFacade;
import models.User;
import utils.UserBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController implements IController {
    private final IService<User> userService;
    private final IUserNotificationFacade notificationFacade;
    private static UserController instance;

    public UserController(IService<User> userService, IUserNotificationFacade notificationFacade) {
        this.userService = userService;
        this.notificationFacade = notificationFacade;
    }

    public static UserController getInstance(IService<User> userService, IUserNotificationFacade notificationFacade) {
        if (instance == null) {
            instance = new UserController(userService, notificationFacade);
        }
        return instance;
    }

    @Override
    public void display() {
        ArrayList<User> users = userService.getAll();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getSurname() + " - " + user.getEmail());
        }
    }

    @Override
    public void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter surname:");
        String surname = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter phone:");
        String phone = scanner.nextLine();

        User newUser = new UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPhone(phone)
                .build();

        userService.add(newUser);

        notificationFacade.sendUserNotifications(newUser);

        User newUser2 = new UserBuilder()
                .setName("Test")
                .setSurname("NoPhone")
                .setEmail("Test@nophone.com")
                .build();

        userService.add(newUser2);

        notificationFacade.sendUserNotifications(newUser2);
    }
}
