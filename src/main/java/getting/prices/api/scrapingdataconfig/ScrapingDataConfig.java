package getting.prices.api.scrapingdataconfig;

import getting.prices.api.site.Site;

import java.util.ArrayList;
import java.util.List;

public class ScrapingDataConfig {

    private Long id;
    private Site site;
    private List<String> patterns;
    private ScrapingDataConfigType type;
}
