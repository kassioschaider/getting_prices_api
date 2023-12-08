package getting.prices.api.service;

import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfigTestRecord;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfigTestResultRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScrapingDataConfigService {

    ScrapingDataConfigTestResultRecord testConfig(ScrapingDataConfigTestRecord record);

    ScrapingDataConfig save(ScrapingDataConfigTestRecord record);

    Page<ScrapingDataConfig> findAll(Pageable pageable);

    ScrapingDataConfig getById(Long id);

    ScrapingDataConfig update(Long id, ScrapingDataConfigTestRecord record);

    void delete(Long id);
}
