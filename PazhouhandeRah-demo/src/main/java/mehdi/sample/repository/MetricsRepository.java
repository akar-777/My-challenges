package mehdi.sample.repository;

import mehdi.sample.DTO.MetricDTO;

import java.util.List;

public interface MetricsRepository {
    List<MetricDTO> calculateMetricsFromAppAndCountry();

}
