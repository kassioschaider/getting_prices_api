package getting.prices.api.service;

import getting.prices.api.domain.price.PriceListRecord;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfigRecord;

import java.util.ArrayList;
import java.util.List;

public interface ScrapingDataConfigService {

    List<PriceListRecord> testConfig(ScrapingDataConfigRecord record);

    ScrapingDataConfig save(ScrapingDataConfigRecord record);
}
