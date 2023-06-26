
package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class TrainService {
    private final XmlReader xmlReader;

    public List<String> getTrackSections(String ril100, int trainNumber, int waggonNumber) {
        Station station = xmlReader.readFile(ril100);
        List<Train> trains = getTrains(trainNumber, station);
        List<Waggon> waggons = getWaggons(trains, waggonNumber);
        return getIdentifiers(waggons);
    }

    public List<Train> getTrains(int trainNumber, Station station) {
        return station.getTracks().getTrack().stream()
                .flatMap(track -> track.getTrains().getTrain().stream())
                .filter(train -> train.getTrainNumbers().getTrainNumber() == trainNumber)
                .collect(Collectors.toList());
    }

    public List<Waggon> getWaggons(List<Train> trains, int waggonNumber) {
        return trains.stream()
                .flatMap(train -> train.getWaggons().getWaggon().stream())
                .filter(waggon -> waggon.getNumber() == waggonNumber)
                .collect(Collectors.toList());
    }

    public List<String> getIdentifiers(List<Waggon> waggons) {
        return waggons.stream()
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
