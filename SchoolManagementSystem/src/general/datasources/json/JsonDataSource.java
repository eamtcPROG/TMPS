package general.datasources.json;

import general.interfaces.DataSource;
import general.utils.JsonFileHandler;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class JsonDataSource<T> implements DataSource<T> {

    private final JsonFileHandler jsonFileHandler = JsonFileHandler.getInstance();
    private final Type type;

    public JsonDataSource(Type type) {
        this.type = type;
    }
    @Override
    public List<T> readData(String path) {
        List<T> data = jsonFileHandler.readFile(path, type);
        return (data != null) ? data : new ArrayList<>();
    }

    @Override
    public void writeData(String path, List<T> data) {
        if (data != null && !data.isEmpty()) {
            jsonFileHandler.writeFile(path, data);
        }
    }
}
