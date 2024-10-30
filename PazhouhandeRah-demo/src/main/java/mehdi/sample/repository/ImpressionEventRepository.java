package mehdi.sample.repository;

import mehdi.sample.entity.ImpressionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpressionEventRepository extends JpaRepository<ImpressionEvent, String> {
}
