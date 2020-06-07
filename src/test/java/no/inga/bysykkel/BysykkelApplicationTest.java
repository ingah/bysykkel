package no.inga.bysykkel;

import no.inga.bysykkel.domain.StationInformation;
import no.inga.bysykkel.domain.StationStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("Test går mot live endpoint")
@SpringBootTest
class BysykkelApplicationTest {

	@Autowired
	BysykkelService bysykkelService;

	@Test
	void getStationInformation() {
		StationInformation stationInformation = bysykkelService.retrieveStationInformation();

		assertNotNull(stationInformation.getData().getStations().get(0).getStationId());
		assertNotNull(stationInformation.getData().getStations().get(0).getName());
		assertNotNull(stationInformation.getData().getStations().get(0).getAddress());
		assertNotNull(stationInformation.getData().getStations().get(0).getCapacity());
	}

	@Test
	void getStationStatus() {
		StationStatus stationStatus = bysykkelService.retrieveStationStatus();
		assertNotNull(stationStatus.getData().getStations().get(0).getStationId());
		assertNotNull(stationStatus.getData().getStations().get(0).getNumBikesAvailable());
		assertNotNull(stationStatus.getData().getStations().get(0).getNumBikesAvailable());
	}

}
