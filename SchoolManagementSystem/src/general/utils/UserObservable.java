package general.utils;

import general.interfaces.Observable;
import general.interfaces.Observer;
import java.util.ArrayList;
import java.util.List;

public class UserObservable implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
