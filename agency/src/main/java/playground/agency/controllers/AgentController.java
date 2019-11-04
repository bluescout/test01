package playground.agency.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import playground.agency.services.MetricsService;
import playground.api.MetricsDto;
import playground.api.SettingsDto;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AgentController {

    @Value("${polling.interval:10000}")
    private Integer interval;

    private final MetricsService metricsService;

    @Autowired
    public AgentController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @RequestMapping(value = AgentApi.V1.METRICS, method = RequestMethod.PUT)
    public SettingsDto putMetrics(@PathVariable("computerId") String computerId, @RequestBody MetricsDto metrics, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        log.info("Metrics received from IP: {}, node name: {}, metrics: {}", remoteAddr, computerId, metrics);
        String combinedId = remoteAddr + "_" + computerId;
        metricsService.putMetrics(combinedId, metrics);

        return new SettingsDto(interval);
    }

}
