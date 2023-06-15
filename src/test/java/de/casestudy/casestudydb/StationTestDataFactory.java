package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.*;

import java.util.List;

public class StationTestDataFactory {

    public static Station createStation() {
        Track track = new Track(createTrainList());
        Tracks tracks = new Tracks(List.of(track));
        return new Station(tracks);
    }

    private static Trains createTrainList() {
        Train train1 = new Train(createTrainNumbers(2310), createWaggonList());
        Train train2 = new Train(createTrainNumbers(2320), createWaggonList());
        return new Trains(List.of(train1, train2));
    }

    private static TrainNumbers createTrainNumbers(int trainNumber) {
        return new TrainNumbers(trainNumber);
    }

    private static Waggons createWaggonList() {
        Waggon waggon1 = new Waggon(1, createSectionList(1));
        Waggon waggon2 = new Waggon(2, createSectionList(2));
        Waggon waggon3 = new Waggon(3, createSectionList(1));
        return new Waggons(List.of(waggon1, waggon2, waggon3));
    }

    private static Sections createSectionList(int numberOfIdentifiersFound) {
        if (numberOfIdentifiersFound == 1) {
            return new Sections(List.of("B"));
        } else {
            return new Sections(List.of("C", "D"));
        }
    }

    public static List<Train> createTrainByTrainNumber(int trainNumber) {
        return List.of(new Train(createTrainNumbers(trainNumber), createWaggonList()));
    }

    public static List<Waggon> createWaggonByWaggonNumber(int waggonNumber, int numberOfIdentifiersFound) {
        return List.of(new Waggon(waggonNumber, createSectionList(numberOfIdentifiersFound)));
    }
}
