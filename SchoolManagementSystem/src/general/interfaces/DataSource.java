package general.interfaces;

import java.util.List;

public interface DataSource<T> {
    List<T> readData(String path);
    void writeData(String path, List<T> data);
}
