package utils;

import interfaces.IService;
import models.User;
import services.LoggingService;

import java.util.ArrayList;

public class LoggingServiceAdapter implements IService<User> {
    private IService<User> userService;
    private LoggingService loggingService;

    public LoggingServiceAdapter(LoggingService loggingService,IService<User> service) {
        this.loggingService = loggingService;
        this.userService = service;
    }

    @Override
    public void add(User user) {
        userService.add(user);
        loggingService.logActivity("UserService", user.getEmail(), "Add user");
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = userService.getAll();
        loggingService.logActivity("UserService", "getAll", "Retrieve all users");
        return users;
    }
}
