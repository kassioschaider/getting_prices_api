package getting.prices.api.controller;

import getting.prices.api.domain.site.Site;
import getting.prices.api.domain.site.SiteListRecord;
import getting.prices.api.domain.site.SiteRecord;
import getting.prices.api.service.SiteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("sites")
public class SiteController {

    @Autowired
    private SiteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<SiteListRecord> save(@RequestBody @Valid SiteRecord record, UriComponentsBuilder uriBuilder) {
        var fromDb = service.save(new Site(record));
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(fromDb.getId()).toUri();

        return ResponseEntity.created(uri).body(new SiteListRecord(fromDb));
    }

    @GetMapping
    public ResponseEntity<Page<SiteListRecord>> get(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        var page = service.findAll(pageable).map(SiteListRecord::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteListRecord> getById(@PathVariable Long id) {
        var fromDb = service.getById(id);
        return ResponseEntity.ok(new SiteListRecord(fromDb));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SiteListRecord> update(@PathVariable Long id, @RequestBody @Valid SiteRecord record) {
        return ResponseEntity.ok(new SiteListRecord(service.update(id, new Site(record))));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
