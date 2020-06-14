package no.inga.bysykkel;

import no.inga.bysykkel.domain.Station;
import no.inga.bysykkel.domain.Status;
import no.inga.bysykkel.dto.StationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class BysykkelService {

    private final BysykkelIntegration bysykkelIntegration;
    Logger LOGGER = LoggerFactory.getLogger(BysykkelService.class);

    @Autowired
    public BysykkelService(BysykkelIntegration bysykkelIntegration) {
        this.bysykkelIntegration = bysykkelIntegration;
    }

    public List<StationDto> retrieveStationsWithLocksAndBikes() {
        List<Status> statuses = bysykkelIntegration.retrieveStationStatus().getData().getStatuses();
        List<Station> stations = bysykkelIntegration.retrieveStationInformation().getData().getStations();

        return statuses.stream().map(
                station -> new StationDto(
                        findStationName(station.getStationId(), stations),
                        station.getNumDocksAvailable(),
                        station.getNumBikesAvailable()))
                .sorted(comparing(StationDto::getName))
                .collect(Collectors.toList());
    }

    private String findStationName(String stationId, List<Station> stations) {
        return stations.stream()
                .filter(station -> station.getStationId().equals(stationId))
                .findAny().map(Station::getName)
                .orElseGet(() -> {
                    LOGGER.warn("Name for station with id {} is missing.", stationId);
                    return "Ukjent navn";
                });
    }
}
