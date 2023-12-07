package getting.prices.api.domain.scrapingdataconfig;

import getting.prices.api.domain.price.PriceListRecord;
import getting.prices.api.domain.sellertag.SellerTagListRecord;

import java.math.BigDecimal;
import java.util.List;

public record ScrapingDataConfigTestResultRecord(
        ScrapingDataConfigTestRecord scrapingDataConfigTestRecord,
        Long executionTimeInMilliseconds,
        Integer totalPricesReceived,
        BigDecimal averagePrices,
        List<PriceListRecord> prices,
        List<SellerTagListRecord> newSellerTags) {
}
