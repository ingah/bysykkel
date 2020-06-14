package no.inga.bysykkel;

import no.inga.bysykkel.dto.StationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BysykkelResource {

    private final BysykkelService bysykkelService;

    @Autowired
    public BysykkelResource(BysykkelService bysykkelService) {
        this.bysykkelService = bysykkelService;
    }

    @GetMapping("/stations")
    public ResponseEntity<List<StationDto>> stationsWithLocksAndBikes() {
        List<StationDto> stationsWithLocksAndBikes = bysykkelService.retrieveStationsWithLocksAndBikes();
        return new ResponseEntity<>(stationsWithLocksAndBikes, HttpStatus.OK);
    }
}
