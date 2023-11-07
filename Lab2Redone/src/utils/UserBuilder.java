package utils;

import interfaces.Role;
import models.User;

public class UserBuilder {
    private String name;
    private String surname;
    private String email;
    private Role role;

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public User build() {
        return new User(name, surname, email, role);
    }
}
