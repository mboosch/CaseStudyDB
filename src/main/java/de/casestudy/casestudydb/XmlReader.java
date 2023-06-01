package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.Station;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class XmlReader {
    private final ResourcePatternResolver resourcePatternResolver;

    public Station readFile(String ril100) {

        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath*:*.xml");
            for (Resource xmlFile : resources) {
                if (Objects.requireNonNull(xmlFile.getFilename()).startsWith(ril100+"_")) {
                    try {
                        JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        return  (Station) jaxbUnmarshaller.unmarshal(xmlFile.getInputStream());
                    } catch (JAXBException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
