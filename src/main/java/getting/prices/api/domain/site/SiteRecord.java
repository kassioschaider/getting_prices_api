package getting.prices.api.domain.site;

import jakarta.validation.constraints.NotNull;

import java.net.URL;

public record SiteRecord(
        @NotNull
        URL url,
        @NotNull
        SiteType type) {
}
