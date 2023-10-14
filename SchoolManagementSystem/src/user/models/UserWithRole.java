package user.models;

import user.interfaces.UserDecorator;

public class UserWithRole implements UserDecorator {
    private User user;
    private String role;

    public UserWithRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public void display() {
        user.toString();
        System.out.println("Role: " + role);
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}
