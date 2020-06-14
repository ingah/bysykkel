package no.inga.bysykkel;

import com.github.tomakehurst.wiremock.WireMockServer;
import no.inga.bysykkel.dto.StationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@PropertySource("classpath:application.properties")
class BysykkelServiceIT {

	@Value("${bysykkel.station_information.endpoint}")
	private String informationEndpoint;

	@Value("${bysykkel.station_status.endpoint}")
	private String statusEndpoint;

	@Autowired
	private BysykkelService bysykkelService;

	private static WireMockServer wireMockServer;

	@BeforeEach
	public void setup () {
		wireMockServer = new WireMockServer(9080);
		wireMockServer.start();
		setUpMock();
	}

	@Test
	void skal_retunere_liste_av_stasjoner() {
		List<StationDto> stasjoner = bysykkelService.retrieveStationsWithLocksAndBikes();
		assertEquals(3, stasjoner.size());
		StationDto stasjon = stasjoner.get(0);
		assertEquals("7 Juni Plassen", stasjon.getName());
		assertEquals(4, stasjon.getNumAvailableBikes());
		assertEquals(8, stasjon.getNumAvailableLocks());
	}

	private void setUpMock() {
		wireMockServer.stubFor(get(urlEqualTo(statusEndpoint))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(200)
						.withBodyFile("station-status.json")));
		wireMockServer.stubFor(get(urlEqualTo(informationEndpoint))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(200)
						.withBodyFile("station-information.json")));
	}
}
