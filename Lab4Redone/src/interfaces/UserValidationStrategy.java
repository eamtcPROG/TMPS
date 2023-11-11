package interfaces;

import models.User;

public interface UserValidationStrategy {
    boolean validate(User user);
}
