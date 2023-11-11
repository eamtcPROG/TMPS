package strategies;

import interfaces.UserValidationStrategy;
import models.User;

public class PhoneValidationStrategy implements UserValidationStrategy {
    @Override
    public boolean validate(User user) {
        return user.getPhone() != null && user.getPhone().startsWith("+373");
    }
}

