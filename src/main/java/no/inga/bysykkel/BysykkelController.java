package no.inga.bysykkel;

import no.inga.bysykkel.dto.StationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
public class BysykkelController {
    private final BysykkelService bysykkelService;

    @Autowired
    public BysykkelController(BysykkelService bysykkelService) {
        this.bysykkelService = bysykkelService;
    }

    @GetMapping("/bysykkel")
    public ModelAndView stationsWithLocksAndBikes(Map<String, Object> model) {
        List<StationDto> stationDtos = bysykkelService.retrieveStationsWithLocksAndBikes();
        model.put("stations", stationDtos);
        return new ModelAndView("index", model);
    }
}
