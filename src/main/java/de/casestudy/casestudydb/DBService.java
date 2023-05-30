package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DBService {
    private final XmlReader xmlReader;

    public String getShortCode() {
        Station station = xmlReader.readFile();
        return station.getShortcode();
    }

}
