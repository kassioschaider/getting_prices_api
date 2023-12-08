package getting.prices.api.domain.sellertag;

import getting.prices.api.domain.site.SiteRecord;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SellerTagRecord(Long id, @NotNull String tag, String description, SiteRecord site, LocalDateTime createdAt, Boolean active) {
    public SellerTagRecord(SellerTag sellerTag) {
        this(sellerTag.getId(), sellerTag.getTag(), sellerTag.getDescription(), new SiteRecord(sellerTag.getSite()), sellerTag.getCreatedAt(), sellerTag.getActive());
    }
}
