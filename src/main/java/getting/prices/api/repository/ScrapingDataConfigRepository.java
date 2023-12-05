package getting.prices.api.repository;

import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapingDataConfigRepository extends JpaRepository<ScrapingDataConfig, Long> {
}
