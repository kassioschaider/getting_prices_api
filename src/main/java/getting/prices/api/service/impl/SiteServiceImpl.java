package getting.prices.api.service.impl;

import getting.prices.api.domain.site.Site;
import getting.prices.api.domain.site.SiteRecord;
import getting.prices.api.repository.SiteRepository;
import getting.prices.api.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository repository;

    @Override
    public Site save(Site site) {
        return repository.save(site);
    }

    @Override
    public Page<Site> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Site getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Site update(Long id, SiteRecord data) {
        var fromDb = repository.getReferenceById(id);
        fromDb.setUrl(data.url());
        fromDb.setType(data.type());

        return repository.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        var fromDb = repository.getReferenceById(id);
        repository.delete(fromDb);
    }
}
