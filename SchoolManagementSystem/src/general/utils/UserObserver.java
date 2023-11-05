package general.utils;

import general.interfaces.Observer;

public class UserObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("UserObserver received update: " + message);
    }
}
