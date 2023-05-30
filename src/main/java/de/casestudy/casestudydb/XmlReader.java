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

@NoArgsConstructor
@Component
public class XmlReader {

    public Station readFile() {
        try {
            File xmlFile = new ClassPathResource("ABST_2017-12-07_12-29-38.xml").getFile();

            JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Station) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
