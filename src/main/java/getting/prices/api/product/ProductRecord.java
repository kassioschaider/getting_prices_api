package getting.prices.api.product;

import jakarta.validation.constraints.NotBlank;

public record ProductRecord(
        @NotBlank
        String barCode,
        @NotBlank
        String description) {
}
