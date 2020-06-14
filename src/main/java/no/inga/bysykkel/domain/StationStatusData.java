package no.inga.bysykkel.domain;

import java.util.ArrayList;
import java.util.List;

public class StationStatusData {
    private List<Status> statuses = new ArrayList<>();

    public void setStations(List<Status> stations) {
        this.statuses = stations;
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}

