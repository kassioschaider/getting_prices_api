package getting.prices.api.domain.site;

import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import getting.prices.api.domain.sellertag.SellerTag;

import java.net.URL;
import java.util.List;

public record SiteListRecord(Long id, URL url, SiteType type, List<ScrapingDataConfig> scrapingDataConfigs, List<SellerTag> sellerTags) {

    public SiteListRecord(Site site) {
        this(site.getId(), site.getUrl(), site.getType(), site.getScrapingDataConfigs(), site.getSellerTags());
    }
}
