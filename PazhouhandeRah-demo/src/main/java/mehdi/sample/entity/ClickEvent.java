package mehdi.sample.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "click_event")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "impressionId", length = 500)
    private String impressionId;
    @Column(name = "revenue")
    private double revenue;

    public ClickEvent() {
    }

    public ClickEvent(String impressionId, Double revenue) {
        this.impressionId = impressionId;
        this.revenue = revenue;
    }
}
