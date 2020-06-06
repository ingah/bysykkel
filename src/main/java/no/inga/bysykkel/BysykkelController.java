package no.inga.bysykkel;

import no.inga.bysykkel.domain.Station;
import no.inga.bysykkel.domain.Status;
import no.inga.bysykkel.dto.StationDto;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BysykkelController {
    Logger LOGGER = LoggerFactory.getLogger(BysykkelController.class);
    private final BysykkelService bysykkelService;

    @Autowired
    public BysykkelController(BysykkelService bysykkelService) {
        this.bysykkelService = bysykkelService;
    }


    @GetMapping("/bysykkel")
    public ModelAndView displayArticle(Map<String, Object> model) {

        List<Status> statuses = bysykkelService.retrieveStationStatus().getData().getStations();
        List<Station> stations = bysykkelService.retrieveStationInformation().getData().getStations();

        List<StationDto> stationDtos = statuses.stream().map(
                station -> new StationDto(
                        getStationName(station.getStationId(), stations),
                        station.getNumDocksAvailable(),
                        station.getNumBikesAvailable()))
                .collect(Collectors.toList());
        model.put("stations", stationDtos);

        return new ModelAndView("index", model);
    }

    private String getStationName(String stationId, List<Station> stations) {
        return stations.stream()
                .filter(station -> station.getStationId().equals(stationId))
                .findAny().map(Station::getName)
                .orElseGet(() -> {
                    LOGGER.warn("Name for staion with id {} is missing.", stationId);
                    return "Ukjent navn";
                });
    }
}
