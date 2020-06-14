package no.inga.bysykkel;

import no.inga.bysykkel.domain.*;
import no.inga.bysykkel.dto.StationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BysykkelServiceTest {

    @Mock
    BysykkelIntegration bysykkelIntegration;

    BysykkelService bysykkelService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bysykkelService = new BysykkelService(bysykkelIntegration);
    }

    @Test
    void skal_mappe_riktig_navn_til_stasjon() {
        when(bysykkelIntegration.retrieveStationInformation()).thenReturn(createStationInformation());
        when(bysykkelIntegration.retrieveStationStatus()).thenReturn(createStationStatus());
        List<StationDto> stationsWithLocksAndBikes = bysykkelService.retrieveStationsWithLocksAndBikes();
        assertEquals("Aker Brygge", stationsWithLocksAndBikes.get(0).getName());
        assertEquals(1, stationsWithLocksAndBikes.get(0).getNumAvailableBikes());
        assertEquals("Carl Berner", stationsWithLocksAndBikes.get(1).getName());
        assertEquals(3, stationsWithLocksAndBikes.get(1).getNumAvailableBikes());
    }

    @Test
    void skal_sette_navn_ukjent_om_info_mangler() {
        StationInformation stationInformation = createStationInformation();
        stationInformation.getData().getStations().remove(0);
        when(bysykkelIntegration.retrieveStationInformation()).thenReturn(stationInformation);
        when(bysykkelIntegration.retrieveStationStatus()).thenReturn(createStationStatus());
        List<StationDto> stationsWithLocksAndBikes = bysykkelService.retrieveStationsWithLocksAndBikes();
        assertEquals("Ukjent navn", stationsWithLocksAndBikes.get(1).getName());
        assertEquals(1, stationsWithLocksAndBikes.get(1).getNumAvailableBikes());
    }

    private StationStatus createStationStatus() {
        StationStatus stationStatus = new StationStatus();
        StationStatusData stationStatusData = new StationStatusData();
        stationStatus.setData(stationStatusData);
        Status status1 = new Status();
        status1.setNumBikesAvailable(1);
        status1.setNumDocksAvailable(2);
        status1.setStationId("1");
        Status status2 = new Status();
        status2.setNumBikesAvailable(3);
        status2.setNumDocksAvailable(4);
        status2.setStationId("2");
        stationStatusData.getStatuses().add(status1);
        stationStatusData.getStatuses().add(status2);
        return stationStatus;
    }

    private StationInformation createStationInformation() {
        StationInformation stationInformation = new StationInformation();
        StationInformationData data = new StationInformationData();
        stationInformation.setData(data);
        Station station1 = new Station();
        station1.setName("Aker Brygge");
        station1.setStationId("1");
        data.getStations().add(station1);
        Station station2 = new Station();
        station2.setName("Carl Berner");
        station2.setStationId("2");
        data.getStations().add(station2);
        return stationInformation;
    }
}
