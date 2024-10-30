package mehdi.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="impression_event")
public class ImpressionEvent {
    @Id
    @Column(name = "impressionId", nullable = false, length = 500)
    private String impressionId;
    @Column(name = "appId")
    private int appId;
    @Column(name = "countryCode", length = 2)
    private String countryCode;
    @Column(name = "advertiserId")
    private int advertiserId;

    public ImpressionEvent() {
    }

    public ImpressionEvent(String impressionId, int appId, String countryCode, int advertiserId) {
        this.impressionId = impressionId;
        this.appId = appId;
        this.countryCode = countryCode;
        this.advertiserId = advertiserId;
    }
}
