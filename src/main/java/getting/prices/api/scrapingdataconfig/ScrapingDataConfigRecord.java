package getting.prices.api.scrapingdataconfig;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ScrapingDataConfigRecord(
        @NotNull
        Long siteId,
        @NotNull List<String> domPatterns,
        String extractPricePattern,
        @NotNull
        String barCodeProductTest,
        @NotNull ScrapingDataConfigType type) {
}
