package getting.prices.api.domain.price;

import java.math.BigDecimal;

public record PriceListRecord(BigDecimal value, String seller) {
}
