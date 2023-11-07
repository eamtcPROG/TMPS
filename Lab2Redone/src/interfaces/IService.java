package interfaces;

import java.util.ArrayList;

public interface IService<T> {
    void add(T object);
    ArrayList<T> getAll();
}
