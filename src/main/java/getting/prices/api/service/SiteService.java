package getting.prices.api.service;

import getting.prices.api.domain.product.Product;
import getting.prices.api.domain.site.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SiteService {

    Site save(Site site);

    Page<Site> findAll(Pageable pageable);

    Site getById(Long id);

    Site update(Long id, Site data);

    void delete(Long id);
}
