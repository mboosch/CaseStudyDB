package de.casestudy.casestudydb.model;

import lombok.Data;

@Data
public class Train {
    private TrainNumbers trainNumbers;
    private Waggons waggons;
}
