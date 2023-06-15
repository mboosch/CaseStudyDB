package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class DBServiceTest {
    private final XmlReader xmlReader = mock(XmlReader.class);
    private final DBService testDbService = spy(new DBService(xmlReader));

    Station testStation = StationTestDataFactory.createStation();
    List<Train> trainListForSpecificTrainNumber = StationTestDataFactory.createTrainByTrainNumber(2310);
    List<Waggon> waggonListWithOneIdentifier = StationTestDataFactory.createWaggonByWaggonNumber(3, 1);

    @Test
    public void getTrackSectionsShouldReturnCorrectSectionsForExistingParameters() {
        String ril100 = "FF";
        when(xmlReader.readFile(ril100)).thenReturn(testStation);
        when(testDbService.getTrains(2310, testStation)).thenReturn(trainListForSpecificTrainNumber);
        when(testDbService.getWaggons(trainListForSpecificTrainNumber, 3)).thenReturn(waggonListWithOneIdentifier);
        when(testDbService.getIdentifiers(waggonListWithOneIdentifier)).thenReturn(List.of("B"));
        List<String> expected = List.of("B");
        List<String> actual = testDbService.getTrackSections(ril100, 2310, 3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTrainsShouldReturnCorrectTrainForGivenTrainNumber() {
        List<Train> expected = trainListForSpecificTrainNumber;
        List<Train> actual = testDbService.getTrains(2310, testStation);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTrainsShouldReturnEmptyListIfTrainNumberNotFound() {
        int trainNumber = 1;
        List<Train> expected = List.of();
        List<Train> actual = testDbService.getTrains(trainNumber, testStation);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getWaggonsShouldReturnCorrectWaggonListForWaggonNumber() {
        int waggonNumber = 3;
        List<Waggon> expected = waggonListWithOneIdentifier;
        List<Waggon> actual = testDbService.getWaggons(trainListForSpecificTrainNumber, waggonNumber);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getWaggonsShouldReturnEmptyListIfWaggonNumberNotFound() {
        int waggonNumber = 25;
        List<Waggon> expected = List.of();
        List<Waggon> actual = testDbService.getWaggons(trainListForSpecificTrainNumber, waggonNumber);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getIdentifiersShouldReturnCorrectIdentifierForSingleIdentifierFound() {
        List<String> expected = List.of("B");
        List<String> actual = testDbService.getIdentifiers(waggonListWithOneIdentifier);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getIdentifiersShouldReturnCorrectIdentifierForTwoIdentifierFound() {
        List<String> expected = List.of("C, D");
        List<String> actual = testDbService.getIdentifiers(StationTestDataFactory.createWaggonByWaggonNumber(3, 2));
        Assertions.assertEquals(expected, actual);
    }
}
