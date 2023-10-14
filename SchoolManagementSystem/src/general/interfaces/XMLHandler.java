package general.interfaces;

import java.util.List;
import java.lang.reflect.Type;

public interface XMLHandler {
    <T> List<T> readXMLFile(String path, Type type);
    <T> void writeXMLFile(String path, List<T> data);
}
