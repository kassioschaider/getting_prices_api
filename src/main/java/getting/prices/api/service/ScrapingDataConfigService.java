package getting.prices.api.service;

import getting.prices.api.scrapingdataconfig.ScrapingDataConfigRecord;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ScrapingDataConfigService {

    ArrayList<BigDecimal> testConfig(ScrapingDataConfigRecord record);
}
