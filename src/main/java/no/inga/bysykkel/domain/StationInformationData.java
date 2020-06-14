package no.inga.bysykkel.domain;

import java.util.ArrayList;
import java.util.List;

public class StationInformationData {
    private List<Station> stations = new ArrayList<>();

    private void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }
}

