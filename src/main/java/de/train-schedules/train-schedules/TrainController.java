package de.casestudy.casestudydb;

import de.casestudy.casestudydb.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("station/")
public class TrainController {
    private final TrainService dbTrainService;

    @GetMapping("/{ril100}/train/{trainNumber}/waggon/{waggonNumber}")
    public Response getTrackSection(
            @PathVariable("ril100") String ril100,
            @PathVariable("trainNumber") int trainNumber,
            @PathVariable("waggonNumber") int waggonNumber) {
        return new Response(dbTrainService.getTrackSections(ril100, trainNumber, waggonNumber));
    }
}
