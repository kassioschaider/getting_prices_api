package getting.prices.api.domain.product;

import jakarta.validation.constraints.NotBlank;

public record ProductRecord(
        @NotBlank
        String barCode,
        @NotBlank
        String description) {
}
