package getting.prices.api.site;

import jakarta.persistence.*;
import lombok.*;

import java.net.URL;

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

    public Site(SiteRecord record) {
        this.url = record.url();
        this.type = record.type();
    }
}
