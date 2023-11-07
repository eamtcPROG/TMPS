package models;

import interfaces.Role;

public class AdminRole implements Role {
    @Override
    public String getRole() {
        return "Admin";
    }
}
