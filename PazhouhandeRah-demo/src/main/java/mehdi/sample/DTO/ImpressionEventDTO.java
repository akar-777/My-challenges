package mehdi.sample.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpressionEventDTO {

    @JsonProperty("id")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String impressionId;

    @JsonProperty("app_id")
    private Integer appId;

    @JsonProperty("country_code")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String countryCode;

    @JsonProperty("advertiser_id")
    private Integer advertiserId;

    public ImpressionEventDTO() {
    }

    public ImpressionEventDTO(String impressionId, Integer appId, String countryCode, Integer advertiserId) {
        this.impressionId = impressionId;
        this.appId = appId;
        this.countryCode = countryCode;
        this.advertiserId = advertiserId;
    }
}
