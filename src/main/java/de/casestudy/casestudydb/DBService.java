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

    public List<String> getShortCode(String ril100, int trainNumber, int waggonNumber) {
        Station station = xmlReader.readFile();

        List<Track> trackList = station.getTracks().getTrack();

        List<String> result = new ArrayList<>();
        for (Track track : trackList) {
            List<Train> trainList = track.getTrains().getTrain();
            for (Train train : trainList) {
                if (train.getTrainNumbers().getTrainNumber() == trainNumber) {
                    List<Waggon> waggonList = train.getWaggons().getWaggon();
                    for (Waggon waggon : waggonList) {
                        if (waggon.getNumber() == waggonNumber) {
                            result.add(waggon.getSections().getIdentifier());
                        }
                    }

                }
            }
        }
        return result;
    }
}


// Zugnummer:
//station / tracks / track[] / trains / train[] / trainnumbers / trainnumber
//
//Waggonnumer:
//station / tracks / track[] / trains / train[] / waggons / waggon[] / number
//
//Gleisabschnitt:
//
//
//station / tracks / track[] / trains / train[] / waggons / waggon / sections / identifier

