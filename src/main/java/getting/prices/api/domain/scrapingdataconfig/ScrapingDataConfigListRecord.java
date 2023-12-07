package getting.prices.api.domain.scrapingdataconfig;

import getting.prices.api.domain.site.SiteRecord;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record ScrapingDataConfigListRecord(
        Long id,
        SiteRecord site,
        String extractPricePattern,
        ScrapingDataConfigType type,
        String uniquePriceClass,
        String keyToAttributeElementToGetPrice,
        String attributeValuePrefixToGetPrice,
        String keyToAttributeElementToGetSellerTagName,
        String attributeValuePrefixToGetSellerTagName,
        LocalDateTime createdAt,
        Boolean active
) {
    public ScrapingDataConfigListRecord(ScrapingDataConfig sdc) {
        this(sdc.getId(), new SiteRecord(sdc.getSite()),
                sdc.getExtractPricePattern(), sdc.getType(),
                sdc.getUniquePriceClass(), sdc.getKeyToAttributeElementToGetPrice(),
                sdc.getAttributeValuePrefixToGetPrice(), sdc.getKeyToAttributeElementToGetSellerTagName(),
                sdc.getAttributeValuePrefixToGetSellerTagName(), sdc.getCreatedAt(), sdc.getActive());
    }
}
