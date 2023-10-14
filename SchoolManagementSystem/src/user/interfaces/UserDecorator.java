package user.interfaces;

import user.models.User;

public interface UserDecorator extends Cloneable {
    void display();
    User clone();

}
