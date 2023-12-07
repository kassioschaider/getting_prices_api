package getting.prices.api.domain.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import getting.prices.api.domain.sellertag.SellerTag;
import jakarta.persistence.*;
import lombok.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sites")
@Entity(name = "Site")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Site {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private URL url;
    @Enumerated(EnumType.STRING)
    private SiteType type;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ScrapingDataConfig> scrapingDataConfigs = new ArrayList<>();

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<SellerTag> sellerTags = new ArrayList<>();

    public Site(SiteRecord record) {
        this.url = record.url();
        this.type = record.type();
    }
}
