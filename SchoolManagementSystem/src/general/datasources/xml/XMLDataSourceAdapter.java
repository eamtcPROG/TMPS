package general.datasources.xml;

import general.interfaces.DataSource;
import general.interfaces.XMLHandler;

import java.lang.reflect.Type;
import java.util.List;

public class XMLDataSourceAdapter<T> implements DataSource<T> {
    private final XMLHandler xmlHandler;
    private final Type type;

    public XMLDataSourceAdapter(XMLHandler xmlHandler, Type type) {
        this.xmlHandler = xmlHandler;
        this.type = type;
    }

    @Override
    public List<T> readData(String path) {
        return xmlHandler.readXMLFile(path, type);
    }

    @Override
    public void writeData(String path, List<T> data) {
        xmlHandler.writeXMLFile(path, data);
    }
}

