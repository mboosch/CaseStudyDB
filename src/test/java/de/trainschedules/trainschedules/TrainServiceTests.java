package de.trainschedules.trainschedules;

import de.trainschedules.trainschedules.model.Station;
import de.trainschedules.trainschedules.model.Train;
import de.trainschedules.trainschedules.model.Waggon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

public class TrainServiceTests {
    private final XmlReader xmlReader = Mockito.mock(XmlReader.class);
    private final TrainService testDbTrainService = spy(new TrainService(xmlReader));

    Station testStation = de.trainschedules.trainschedule.TestDataFactory.createStation();
    List<Train> trainListForSpecificTrainNumber = de.trainschedules.trainschedule.TestDataFactory.createTrainByTrainNumber(2310);
    List<Waggon> waggonListWithOneIdentifier = de.trainschedules.trainschedule.TestDataFactory.createWaggonByWaggonNumber(3, 1);

    @Test
    public void getTrackSectionsShouldReturnCorrectSectionsForExistingParameters() {
        String ril100 = "FF";
        when(xmlReader.readFile(ril100)).thenReturn(testStation);
        when(testDbTrainService.getTrains(2310, testStation)).thenReturn(trainListForSpecificTrainNumber);
        when(testDbTrainService.getWaggons(trainListForSpecificTrainNumber, 3)).thenReturn(waggonListWithOneIdentifier);
        when(testDbTrainService.getIdentifiers(waggonListWithOneIdentifier)).thenReturn(List.of("B"));
        List<String> expected = List.of("B");
        List<String> actual = testDbTrainService.getTrackSections(ril100, 2310, 3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTrainsShouldReturnCorrectTrainForGivenTrainNumber() {
        List<Train> expected = trainListForSpecificTrainNumber;
        List<Train> actual = testDbTrainService.getTrains(2310, testStation);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTrainsShouldReturnEmptyListIfTrainNumberNotFound() {
        int trainNumber = 1;
        List<Train> expected = List.of();
        List<Train> actual = testDbTrainService.getTrains(trainNumber, testStation);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getWaggonsShouldReturnCorrectWaggonListForWaggonNumber() {
        int waggonNumber = 3;
        List<Waggon> expected = waggonListWithOneIdentifier;
        List<Waggon> actual = testDbTrainService.getWaggons(trainListForSpecificTrainNumber, waggonNumber);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getWaggonsShouldReturnEmptyListIfWaggonNumberNotFound() {
        int waggonNumber = 25;
        List<Waggon> expected = List.of();
        List<Waggon> actual = testDbTrainService.getWaggons(trainListForSpecificTrainNumber, waggonNumber);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getIdentifiersShouldReturnCorrectIdentifierForSingleIdentifierFound() {
        List<String> expected = List.of("B");
        List<String> actual = testDbTrainService.getIdentifiers(waggonListWithOneIdentifier);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getIdentifiersShouldReturnCorrectIdentifierForTwoIdentifierFound() {
        List<String> expected = List.of("C, D");
        List<String> actual = testDbTrainService.getIdentifiers(de.trainschedules.trainschedule.TestDataFactory.createWaggonByWaggonNumber(3, 2));
        Assertions.assertEquals(expected, actual);
    }
}
