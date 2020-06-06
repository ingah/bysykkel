package no.inga.bysykkel.dto;

public class StationDto {
    private final String name;
    private final int numAvailableLocks;
    private final int numAvailableBikes;

    public StationDto(String name, int numAvailableLocks, int numAvailableBikes) {
        this.name = name;
        this.numAvailableLocks = numAvailableLocks;
        this.numAvailableBikes = numAvailableBikes;
    }

    public String getName() {
        return name;
    }

    public int getNumAvailableLocks() {
        return numAvailableLocks;
    }

    public int getNumAvailableBikes() {
        return numAvailableBikes;
    }
}
