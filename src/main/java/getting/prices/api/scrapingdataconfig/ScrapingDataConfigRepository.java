package getting.prices.api.scrapingdataconfig;

import getting.prices.api.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapingDataConfigRepository extends JpaRepository<ScrapingDataConfig, Long> {
}
