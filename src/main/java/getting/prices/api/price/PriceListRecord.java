package getting.prices.api.price;

import java.math.BigDecimal;

public record PriceListRecord(BigDecimal value, String seller) {
}
