package models;

import interfaces.Role;

public class MemberRole implements Role {
    @Override
    public String getRole() {
        return "Member";
    }
}
