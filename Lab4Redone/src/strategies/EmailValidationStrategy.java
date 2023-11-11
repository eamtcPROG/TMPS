package strategies;

import interfaces.UserValidationStrategy;
import models.User;

public class EmailValidationStrategy implements UserValidationStrategy {
    @Override
    public boolean validate(User user) {
        return user.getEmail() != null && user.getEmail().contains("@");
    }
}
