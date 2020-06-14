package no.inga.bysykkel;

import no.inga.bysykkel.domain.StationInformation;
import no.inga.bysykkel.domain.StationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BysykkelIntegration {

    @Value("${bysykkel.station_information.endpoint}")
    private String informationEndpoint;

    @Value("${bysykkel.station_status.endpoint}")
    private String statusEndpoint;

    private final WebClient bysykkelClient;

    @Autowired
    public BysykkelIntegration(WebClient bysykkelClient) {
        this.bysykkelClient = bysykkelClient;
    }

    StationInformation retrieveStationInformation() {
        return bysykkelClient
                .get()
                .uri(informationEndpoint)
                .retrieve()
                .bodyToMono(StationInformation.class)
                .block();
    } //Ingen feilhåndtering her, mottar vi ikke dataene er det uansett ingenting vi kan
    // gjøre for brukeren, det er bare å vise error.html.

    StationStatus retrieveStationStatus() {
        return bysykkelClient
                .get()
                .uri(statusEndpoint)
                .retrieve()
                .bodyToMono(StationStatus.class)
                .block();
    }
}
