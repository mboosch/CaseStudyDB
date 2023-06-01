package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DBService {
    private final XmlReader xmlReader;

    public List<String> getTrackSection(String ril100, int trainNumber, int waggonNumber) {
        Station station = xmlReader.readFile(ril100);

        List<String> result = new ArrayList<>();

        List<Track> trackList = station.getTracks().getTrack();
        for (Track track : trackList) {
            List<Train> trainList = track.getTrains().getTrain();
            for (Train train : trainList) {
                if (train.getTrainNumbers().getTrainNumber() == trainNumber) {
                    List<Waggon> waggonList = train.getWaggons().getWaggon();
                    for (Waggon waggon : waggonList) {
                        if (waggon.getNumber() == waggonNumber) {
                            List<String> identifierList = waggon.getSections().getIdentifier();
                            if (identifierList.size() == 2) {
                                result.add(identifierList.get(0) + ", " + identifierList.get(1));
                            }
                            else {
                                result.add(identifierList.get(0));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
