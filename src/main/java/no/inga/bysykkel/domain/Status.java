package no.inga.bysykkel.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Status {
    @JsonProperty("is_installed")
    private Integer isInstalled;
    @JsonProperty("is_renting")
    private Integer isRenting;
    @JsonProperty("num_bikes_available")
    private Integer numBikesAvailable;
    @JsonProperty("num_docks_available")
    private Integer numDocksAvailable;
    @JsonProperty("last_reported")
    private Integer lastReported;
    @JsonProperty("is_returning")
    private Integer isReturning;
    @JsonProperty("station_id")
    private String stationId;

    public Integer getIsInstalled() {
        return isInstalled;
    }

    public void setIsInstalled(Integer isInstalled) {
        this.isInstalled = isInstalled;
    }

    public Integer getIsRenting() {
        return isRenting;
    }

    public void setIsRenting(Integer isRenting) {
        this.isRenting = isRenting;
    }

    public Integer getNumBikesAvailable() {
        return numBikesAvailable;
    }

    public void setNumBikesAvailable(Integer numBikesAvailable) {
        this.numBikesAvailable = numBikesAvailable;
    }

    public Integer getNumDocksAvailable() {
        return numDocksAvailable;
    }

    public void setNumDocksAvailable(Integer numDocksAvailable) {
        this.numDocksAvailable = numDocksAvailable;
    }

    public Integer getLastReported() {
        return lastReported;
    }

    public Date getLastReportedDate() {
        return new Date(lastReported);
    }

    public void setLastReported(Integer lastReported) {
        this.lastReported = lastReported;
    }

    public Integer getIsReturning() {
        return isReturning;
    }

    public void setIsReturning(Integer isReturning) {
        this.isReturning = isReturning;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
