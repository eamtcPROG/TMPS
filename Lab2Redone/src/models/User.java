package models;

import interfaces.Role;

public class User {
    private String name;
    private String surname;
    private String email;

    private Role role;

    public User(String name, String surname, String email,Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole(){
        return role;
    }
}
