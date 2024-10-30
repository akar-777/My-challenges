package mehdi.sample.initialize;

import mehdi.sample.DTO.ClickEventDTO;
import mehdi.sample.DTO.ImpressionEventDTO;
import mehdi.sample.entity.ClickEvent;
import mehdi.sample.entity.ImpressionEvent;
import mehdi.sample.repository.ClickEventRepository;
import mehdi.sample.repository.ImpressionEventRepository;
import mehdi.sample.utility.JsonFilesUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {
    private ClickEventRepository clickEventRepository;
    private ImpressionEventRepository impressionEventRepository;
    private JsonFilesUtils jsonFilesUtils;

    public DataInitializer(ClickEventRepository clickEventRepository, ImpressionEventRepository impressionEventRepository, JsonFilesUtils jsonFilesUtils) {
        this.clickEventRepository = clickEventRepository;
        this.impressionEventRepository = impressionEventRepository;
        this.jsonFilesUtils = jsonFilesUtils;
    }

    @PostConstruct
    public void init() {

        int counter = 0;
        // only for check impression events if null push some data into it's table on the database.
        List<ImpressionEvent> allImpressionEvents = impressionEventRepository.findAll();
        if (allImpressionEvents != null && allImpressionEvents.isEmpty()) {
            // push impression events data to database.
            List<ImpressionEventDTO> impressionEventDTOS = jsonFilesUtils.readImpressionEvents();
            for (ImpressionEventDTO impressionEventDTO : impressionEventDTOS) {
                ImpressionEvent impressionEvent = new ImpressionEvent(impressionEventDTO.getImpressionId()==null? UUID.randomUUID().toString():impressionEventDTO.getImpressionId(),
                        impressionEventDTO.getAppId() == null ? 0 : impressionEventDTO.getAppId(),
                        impressionEventDTO.getCountryCode(),
                        impressionEventDTO.getAdvertiserId() == null ? 0 : impressionEventDTO.getAdvertiserId());
                if (counter == 1458) {
                    System.out.println("catching");
                }
                impressionEventRepository.save(impressionEvent);
                System.out.println(++counter);
            }
        }

        // only for check click events if null push some data into it's table on the database.
        List<ClickEvent> allClickEvents = clickEventRepository.findAll();
        if (allClickEvents != null && allClickEvents.isEmpty()) {
            // push click events data too database
            List<ClickEventDTO> clickEventDTOS = jsonFilesUtils.readClickEvents();
            for (ClickEventDTO clickEventDTO : clickEventDTOS) {
                ClickEvent clickEvent = new ClickEvent(clickEventDTO.getImpressionId(), clickEventDTO.getRevenue());

                clickEventRepository.save(clickEvent);
            }
        }
    }
}
