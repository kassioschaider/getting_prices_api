package getting.prices.api.site;

import java.net.URL;

public record SiteListRecord(Long id, URL url, SiteType type) {

    public SiteListRecord(Site site) {
        this(site.getId(), site.getUrl(), site.getType());
    }
}
