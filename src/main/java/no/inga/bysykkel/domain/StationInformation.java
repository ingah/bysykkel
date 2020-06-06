package no.inga.bysykkel.domain;

public class StationInformation {
    private long lastUpdated;
    private StationInformationData data;

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setData(StationInformationData data) {
        this.data = data;
    }

    public StationInformationData getData() {
        return data;
    }
}
