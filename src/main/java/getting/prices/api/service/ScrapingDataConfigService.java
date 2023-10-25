package getting.prices.api.service;

import getting.prices.api.price.PriceListRecord;
import getting.prices.api.scrapingdataconfig.ScrapingDataConfigRecord;

import java.util.ArrayList;

public interface ScrapingDataConfigService {

    ArrayList<PriceListRecord> testConfig(ScrapingDataConfigRecord record);
}
