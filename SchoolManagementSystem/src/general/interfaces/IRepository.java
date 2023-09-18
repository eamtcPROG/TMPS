package general.interfaces;

import java.util.List;

public interface IRepository<T> {
    T findById(int id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(int id);
}
