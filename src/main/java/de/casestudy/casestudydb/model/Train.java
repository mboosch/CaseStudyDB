package de.casestudy.casestudydb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    private TrainNumbers trainNumbers;
    private Waggons waggons;
}
