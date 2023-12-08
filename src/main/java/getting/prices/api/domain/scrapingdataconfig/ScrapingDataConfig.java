package getting.prices.api.domain.scrapingdataconfig;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import getting.prices.api.domain.site.Site;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "scraping_data_configs")
@Entity(name = "ScrapingDataConfig")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ScrapingDataConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    @JsonManagedReference
    private Site site;

    @Column( name= "extract_price_pattern")
    private String extractPricePattern;
    @Enumerated(EnumType.STRING)
    private ScrapingDataConfigType type;
    @Column( name = "unique_price_class")
    private String uniquePriceClass;
    @Column( name = "key_to_attribute_element_to_get_price")
    private String keyToAttributeElementToGetPrice;
    @Column( name = "attribute_value_prefix_to_get_price")
    private String attributeValuePrefixToGetPrice;
    @Column( name = "key_to_attribute_element_to_get_seller_tag_name")
    private String keyToAttributeElementToGetSellerTagName;
    @Column( name = "attribute_value_prefix_to_get_seller_tag_name")
    private String attributeValuePrefixToGetSellerTagName;
    @Column( name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column( name = "active")
    private Boolean active;

    public ScrapingDataConfig(final ScrapingDataConfigTestRecord record, Site site) {
        this.site = site;
        this.extractPricePattern = record.extractPricePattern();
        this.type = record.type();
        this.uniquePriceClass = record.uniquePriceClass();
        this.keyToAttributeElementToGetPrice = record.keyToAttributeElementToGetPrice();
        this.attributeValuePrefixToGetPrice = record.attributeValuePrefixToGetPrice();
        this.keyToAttributeElementToGetSellerTagName = record.keyToAttributeElementToGetSellerTagName();
        this.attributeValuePrefixToGetSellerTagName = record.attributeValuePrefixToGetSellerTagName();
        this.active = true;
    }

}
