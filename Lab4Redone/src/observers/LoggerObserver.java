package observers;

import interfaces.Observer;

public class LoggerObserver implements Observer {
    @Override
    public void update() {
        System.out.println("LoggerObserver: event occurred.");
    }
}

