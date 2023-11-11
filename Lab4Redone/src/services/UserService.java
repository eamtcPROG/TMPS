package services;

import interfaces.IService;
import models.User;

import java.util.ArrayList;

public class UserService implements IService<User> {
    private final ArrayList<User> users = new ArrayList<>();
    private static UserService instance;

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void add(User user){
        users.add(user);
    }

    @Override
    public ArrayList<User> getAll() {
        return users;
    }
}
