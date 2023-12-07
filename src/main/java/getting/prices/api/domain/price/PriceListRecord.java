package getting.prices.api.domain.price;

import java.math.BigDecimal;

public record PriceListRecord(BigDecimal value, String sellerTag) {
    public PriceListRecord(Price price) {
        this(price.getValue(), price.getSellerTag());
    }
}
