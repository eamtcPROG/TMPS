package controllers;

import interfaces.IControllerUser;
import interfaces.IService;

import interfaces.UserValidationStrategy;
import models.User;
import observers.UserEventManager;
import utils.UserBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController implements IControllerUser {
    private final IService<User> userService;
    private static UserController instance;
    private final UserEventManager eventManager;

    private UserValidationStrategy validationStrategy;

    public UserController(IService<User> userService, UserValidationStrategy validationStrategy, UserEventManager eventManager) {
        this.userService = userService;
        this.validationStrategy = validationStrategy;
        this.eventManager = eventManager;
    }

    public void setValidationStrategy(UserValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public static UserController getInstance(IService<User> userService,UserValidationStrategy validationStrategy,UserEventManager eventManager) {
        if (instance == null) {
            instance = new UserController(userService,validationStrategy,eventManager);
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

        if (validationStrategy.validate(newUser)) {
            userService.add(newUser);
            System.out.println("User created successfully.");
            eventManager.notifyObservers();
        } else {
            System.out.println("User validation failed.");
        }

    }
}
