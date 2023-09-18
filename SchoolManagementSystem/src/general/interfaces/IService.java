package general.interfaces;

import java.util.List;

public interface IService<T> {
    T findById(int id);
    List<T> getAll();
    T saveOrUpdate(T entity);
    void delete(int id);
}

