package mehdi.sample.controller;

import mehdi.sample.DTO.MetricDTO;
import mehdi.sample.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetricController {

    private MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("/metrics")
    public ResponseEntity<List<MetricDTO>> getMetrics() {
        List<MetricDTO> metricsFromAppAndCountry = metricService.getMetricsFromAppAndCountry();
        return new ResponseEntity<>(metricsFromAppAndCountry, HttpStatus.OK);
    }
    @GetMapping("/recommendation")
    public ResponseEntity<String> getRecommendation() {
        return new ResponseEntity<>(metricService.getTopAdvertisersFromAppAndCountry(), HttpStatus.OK);
    }
}
