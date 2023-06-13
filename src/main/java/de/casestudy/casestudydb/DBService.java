
package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class DBService {
    private final XmlReader xmlReader;

    public List<String> getTrackSections(String ril100, int trainNumber, int waggonNumber) {

        Station station = xmlReader.readFile(ril100);
        List<Waggon> waggonList = getTrain(trainNumber, station);
        return getIdentifier(waggonList, waggonNumber);
    }

    public List<Waggon> getTrain(int trainNumber, Station station) {
        return station.getTracks().getTrack().stream()
                .flatMap(track -> track.getTrains().getTrain().stream())
                .filter(train -> train.getTrainNumbers().getTrainNumber() == trainNumber)
                .flatMap(train -> train.getWaggons().getWaggon().stream())
                .collect(Collectors.toList());
    }

    public List<String> getIdentifier(List<Waggon> waggonList, int waggonNumber) {
        return waggonList.stream()
                .filter(waggon -> waggon.getNumber() == waggonNumber)
                .flatMap(waggon -> {
                    List<String> identifierList = waggon.getSections().getIdentifier();
                    Stream<String> stream;
                    if (identifierList.size() == 2) {
                        stream = Stream.of(identifierList.get(0) + ", " + identifierList.get(1));
                    } else {
                        stream = identifierList.stream();
                    }
                    return stream;
                })
                .collect(Collectors.toList());
    }
}
