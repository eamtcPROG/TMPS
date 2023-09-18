package general.utils;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class JsonFileHandler {

    private static JsonFileHandler instance = null;
    private final Gson gson = new Gson();

    private JsonFileHandler() { }

    public static JsonFileHandler getInstance() {
        if (instance == null) {
            instance = new JsonFileHandler();
        }
        return instance;
    }

    public <T> T readFile(String path, Type type) {
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> void writeFile(String path, T data) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
