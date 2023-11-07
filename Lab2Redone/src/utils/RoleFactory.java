package utils;
import interfaces.IFactory;
import interfaces.Role;
import models.AdminRole;
import models.MemberRole;

public class RoleFactory implements IFactory<Role> {

    @Override
    public Role create(String type) {
        if ("admin".equalsIgnoreCase(type)) {
            return new AdminRole();
        } else if ("member".equalsIgnoreCase(type)) {
            return new MemberRole();
        }
        throw new IllegalArgumentException("Unknown role type: " + type);
    }
}
