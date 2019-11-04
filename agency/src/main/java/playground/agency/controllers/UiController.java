package playground.agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import playground.agency.services.MetricsService;
import playground.api.MetricsDto;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UiController {
    private final MetricsService metricsService;

    @Autowired
    public UiController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @RequestMapping(value = UiApi.V1.COMPUTERS, method = RequestMethod.GET)
    public Collection<String> getComputers() {
        return metricsService.getComputers();
    }

    @RequestMapping(value = UiApi.V1.METRICS, method = RequestMethod.GET)
    public MetricsDto getCurrentMetrics(@PathVariable("computerId") String computerId) {
        return metricsService.getCurrentMetrics(computerId);
    }

    @RequestMapping(value = UiApi.V1.METRICS_HISTORY, method = RequestMethod.GET)
    public Collection<MetricsDto> getHistoricalMetrics(@PathVariable("computerId") String computerId) {
        return metricsService.getHistoricalMetrics(computerId);
    }



}
