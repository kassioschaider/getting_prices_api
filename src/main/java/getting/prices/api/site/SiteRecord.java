package getting.prices.api.site;

import jakarta.validation.constraints.NotNull;

import java.net.URL;

public record SiteRecord(
        @NotNull
        URL url,
        @NotNull
        SiteType type) {
}
