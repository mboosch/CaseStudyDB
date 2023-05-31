package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.Station;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@NoArgsConstructor
@Component
public class XmlReader {

    public Station readFile(String ril100) {
        ClassLoader classLoader = getClass().getClassLoader();
        File folder = new File(Objects.requireNonNull(classLoader.getResource(".")).getFile());
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));
        if (files != null) {
            for (File xmlFile : files) {
                if (xmlFile.getName().startsWith(ril100)) {
                    try {
                        JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        return (Station) jaxbUnmarshaller.unmarshal(xmlFile);
                    } catch (JAXBException e) {
                        throw new RuntimeException(e);
                    }
                }
            }}
        return null;
        }
    }
