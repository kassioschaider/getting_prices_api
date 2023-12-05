package getting.prices.api.repository;

import getting.prices.api.domain.site.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
}
