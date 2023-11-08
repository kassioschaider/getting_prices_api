package getting.prices.api.domain.scrapingdataconfig;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "scrapingDataConfigs")
@Entity(name = "ScrapingDataConfig")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScrapingDataConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long siteId;
    private String extractPricePattern;
    private String barCodeProductTest;
    private ScrapingDataConfigType type;
    private String uniquePriceClass;
    private String keyToAttributeElementToGetPrice;
    private String attributeValuePrefixToGetPrice;
    private String keyToAttributeElementToGetSellerTagName;
    private String attributeValuePrefixToGetSellerTagName;

    public ScrapingDataConfig(final ScrapingDataConfigRecord record) {
        this.siteId = record.siteId();
        this.extractPricePattern = record.extractPricePattern();
        this.barCodeProductTest = record.barCodeProductTest();
        this.type = record.type();
        this.uniquePriceClass = record.uniquePriceClass();
        this.keyToAttributeElementToGetPrice = record.keyToAttributeElementToGetPrice();
        this.attributeValuePrefixToGetPrice = record.attributeValuePrefixToGetPrice();
        this.keyToAttributeElementToGetSellerTagName = record.keyToAttributeElementToGetSellerTagName();
        this.attributeValuePrefixToGetSellerTagName = record.attributeValuePrefixToGetSellerTagName();
    }
}
