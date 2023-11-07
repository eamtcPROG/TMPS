package controllers;

import interfaces.IController;
import interfaces.IService;
import interfaces.Role;
import interfaces.IFactory;
import models.User;
import utils.UserBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController implements IController {
    private final IService<User> userService;
    private static UserController instance;
    private IFactory<Role> roleFactory;

    public UserController(IService<User> userService, IFactory<Role> roleFactory) {
        this.userService = userService;
        this.roleFactory = roleFactory;
    }

    public static UserController getInstance(IService<User> userService, IFactory<Role> roleFactory) {
        if (instance == null) {
            instance = new UserController(userService,roleFactory);
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

        Role role = roleFactory.create("member");
        User newUser = new UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setRole(role)
                .build();

        userService.add(newUser);
        System.out.println("User created: " + newUser.getName() + " " + newUser.getSurname() + " - " + newUser.getEmail()
                + " " + newUser.getRole().getRole());
        role = roleFactory.create("admin");
        User newUser2 = new UserBuilder()
                .setEmail("admin@g.com")
                .setRole(role)
                .build();

        userService.add(newUser2);
        System.out.println("User created: "  + newUser2.getEmail() + " " + newUser2.getRole().getRole());
    }
}
