package getting.prices.api.domain.scrapingdataconfig;

import jakarta.validation.constraints.NotNull;

public record ScrapingDataConfigRecord(
        @NotNull Long siteId,
        @NotNull String extractPricePattern,
        @NotNull String barCodeProductTest,
        @NotNull ScrapingDataConfigType type,
        String uniquePriceClass,
        String keyToAttributeElementToGetPrice,
        String attributeValuePrefixToGetPrice,
        String keyToAttributeElementToGetSellerTagName,
        String attributeValuePrefixToGetSellerTagName) {
}
