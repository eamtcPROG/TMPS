package general.datasources.xml;

import general.interfaces.XMLHandler;

import java.lang.reflect.Type;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLFileHandler implements XMLHandler {

    @Override
    public <T> List<T> readXMLFile(String path, Type type) {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance((Class) type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (List<T>) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> void writeXMLFile(String path, List<T> data) {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(data.get(0).getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(data, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}


