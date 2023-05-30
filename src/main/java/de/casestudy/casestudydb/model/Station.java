package de.casestudy.casestudydb.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "station")
public class Station {
    private Tracks tracks;

}

