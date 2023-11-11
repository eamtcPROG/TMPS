package proxies;

import interfaces.IService;
import models.User;

import java.util.ArrayList;

public class UserServiceProxy implements IService<User> {
    private IService<User> userService;
    private boolean isAuthorized;

    public UserServiceProxy(IService<User> userService, boolean isAuthorized) {
        this.userService = userService;
        this.isAuthorized = isAuthorized;
    }

    @Override
    public void add(User user) {
        if (isAuthorized) {
            userService.add(user);
        } else {
            System.out.println("Unauthorized attempt to add user.");
        }
    }

    @Override
    public ArrayList<User> getAll() {
        return userService.getAll();
    }
}
