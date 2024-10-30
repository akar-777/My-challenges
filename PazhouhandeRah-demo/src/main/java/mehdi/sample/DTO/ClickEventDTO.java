package mehdi.sample.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClickEventDTO {
    @JsonProperty("impression_id")
    private String impressionId;

    @JsonProperty("revenue")
    private Double revenue;

    public ClickEventDTO() {
    }

    public ClickEventDTO(String impressionId, Double revenue) {
        this.impressionId = impressionId;
        this.revenue = revenue;
    }
}
