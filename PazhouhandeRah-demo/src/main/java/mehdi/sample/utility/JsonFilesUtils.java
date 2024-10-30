package mehdi.sample.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import mehdi.sample.DTO.ClickEventDTO;
import mehdi.sample.DTO.ImpressionEventDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JsonFilesUtils {

    @Value("${impressions.file.path}")
    private String impressionsFilePath;

    @Value("${clicks.file.path}")
    private String clicksFilePath;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ImpressionEventDTO> readImpressionEvents() {
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, ImpressionEventDTO.class);
            return objectMapper.readValue(new File(impressionsFilePath), listType);
        } catch (IOException e) {
            System.err.println("Error reading impressions: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<ClickEventDTO> readClickEvents() {
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, ClickEventDTO.class);
            return objectMapper.readValue(new File(clicksFilePath), listType);
        } catch (IOException e) {
            System.err.println("Error reading clicks: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
