package general.interfaces;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers(String message);
}
