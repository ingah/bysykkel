package no.inga.bysykkel;

import no.inga.bysykkel.domain.StationInformation;
import no.inga.bysykkel.domain.StationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
    } //Ingen feilhåndtering her, mottar vi ikke dataene er det uansett ingenting vi kan
     // gjøre for brukeren, det er bare å vise error.html.

    StationStatus retrieveStationStatus() {
        return bysykkelClient
                .get()
                .uri("station_status.json")
                .retrieve()
                .bodyToMono(StationStatus.class)
                .block();
    }
}
