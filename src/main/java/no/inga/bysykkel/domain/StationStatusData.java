package no.inga.bysykkel.domain;

import java.util.List;

public class StationStatusData {
    private List<Status> stations;

    public void setStations(List<Status> stations) {
        this.stations = stations;
    }

    public List<Status> getStations() {
        return stations;
    }
}

