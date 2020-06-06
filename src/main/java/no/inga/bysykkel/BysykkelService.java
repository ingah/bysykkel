package no.inga.bysykkel;

import no.inga.bysykkel.domain.StationInformation;
import no.inga.bysykkel.domain.StationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BysykkelService {
    private final WebClient bysykkelClient;

    @Autowired
    public BysykkelService(WebClient bysykkelClient) {
        this.bysykkelClient = bysykkelClient;
    }

    StationInformation retrieveStationInformation() {
        return bysykkelClient
                .get()
                .uri("station_information.json")
                .retrieve()
                .bodyToMono(StationInformation.class)
                .block();

    }


    StationStatus retrieveStationStatus() {
        return bysykkelClient
                .get()
                .uri("station_status.json")
                .retrieve()
                .bodyToMono(StationStatus.class)
                .block();
    }
}
