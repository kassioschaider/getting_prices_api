package getting.prices.api.domain.sellertag;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import getting.prices.api.domain.site.Site;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "seller_tags")
@Entity(name = "SellerTag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SellerTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String tag;
    private String description;
    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    @JsonManagedReference
    private Site site;
    @Column( name= "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean active;

    public SellerTag(String tag, Site site) {
        this.tag = tag;
        this.site = site;
        this.active = true;
    }
}
