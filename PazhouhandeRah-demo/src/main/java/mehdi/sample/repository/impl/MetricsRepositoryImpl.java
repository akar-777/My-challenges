package mehdi.sample.repository.impl;

import mehdi.sample.DTO.MetricDTO;
import mehdi.sample.repository.MetricsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MetricsRepositoryImpl implements MetricsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MetricDTO> calculateMetricsFromAppAndCountry() {
        String query = "SELECT new mehdi.sample.DTO.MetricDTO(i.appId, i.countryCode, " +
                "COUNT(i), COUNT(c), SUM(c.revenue)) " +
                "FROM ImpressionEvent i LEFT JOIN ClickEvent c ON i.impressionId = c.impressionId " +
                "GROUP BY i.appId, i.countryCode";
        return entityManager.createQuery(query, MetricDTO.class).getResultList();
    }
}
