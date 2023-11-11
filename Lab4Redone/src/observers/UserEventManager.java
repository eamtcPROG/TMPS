package observers;

import interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class UserEventManager {
    private final List<Observer> observers;

    public UserEventManager(){
        this.observers = new ArrayList<>();
    }
    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}