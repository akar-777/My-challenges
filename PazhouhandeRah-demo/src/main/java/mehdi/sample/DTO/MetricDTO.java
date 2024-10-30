package mehdi.sample.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricDTO {
    @JsonProperty("appId")
    private int appId;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("impressions")
    private Long impressions;

    @JsonProperty("clicks")
    private Long clicks;

    @JsonProperty("revenue")
    private Double revenue;


    public MetricDTO() {
    }

    public MetricDTO(int appId, String countryCode, Long impressions, Long clicks, Double revenue) {
        this.appId = appId;
        this.countryCode = countryCode;
        this.impressions = impressions;
        this.clicks = clicks;
        this.revenue = revenue;
    }
}
