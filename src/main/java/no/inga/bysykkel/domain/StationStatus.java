package no.inga.bysykkel.domain;

public class StationStatus {
    private long lastUpdated;
    private StationStatusData data;

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setData(StationStatusData data) {
        this.data = data;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public StationStatusData getData() {
        return data;
    }
}
