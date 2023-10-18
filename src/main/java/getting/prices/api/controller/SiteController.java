package getting.prices.api.controller;

import getting.prices.api.site.Site;
import getting.prices.api.site.SiteListRecord;
import getting.prices.api.site.SiteRecord;
import getting.prices.api.site.SiteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sites")
public class SiteController {

    @Autowired
    private SiteRepository repository;

    @PostMapping
    @Transactional
    public Site save(@RequestBody @Valid SiteRecord record) {
        return repository.save(new Site(record));
    }

    @GetMapping
    public Page<SiteListRecord> get(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return repository.findAll(pageable).map(SiteListRecord::new);
    }

    @GetMapping("/{id}")
    public Site getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    @Transactional
    public Site update(@PathVariable Long id, @RequestBody @Valid SiteRecord record) {
        Site fromDb = repository.findById(id).orElseThrow();
        fromDb.setUrl(record.url());
        fromDb.setType(record.type());
        return repository.save(fromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
