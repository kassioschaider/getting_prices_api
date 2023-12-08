package getting.prices.api.controller;

import getting.prices.api.domain.scrapingdataconfig.*;
import getting.prices.api.service.ScrapingDataConfigService;
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
@RequestMapping("scrapingDataConfigs")
public class ScrapingDataConfigController {

    @Autowired
    private ScrapingDataConfigService service;

    @PostMapping("/test")
    @Transactional
    public ResponseEntity<ScrapingDataConfigTestResultRecord> testConfig(@RequestBody @Valid ScrapingDataConfigTestRecord record) {
        return ResponseEntity.ok(service.testConfig(record));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ScrapingDataConfigListRecord> save(@RequestBody @Valid ScrapingDataConfigTestRecord record, UriComponentsBuilder uriBuilder) {
        var fromDb = service.save(record);
        var uri = uriBuilder.path("/scrapingDataConfig/{id}").buildAndExpand(fromDb.getId()).toUri();

        return ResponseEntity.created(uri).body(new ScrapingDataConfigListRecord(fromDb));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScrapingDataConfigListRecord> getById(@PathVariable Long id) {
        var fromDb = service.getById(id);
        return ResponseEntity.ok(new ScrapingDataConfigListRecord(fromDb));
    }

    @GetMapping
    public ResponseEntity<Page<ScrapingDataConfigListRecord>> get(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        var page = service.findAll(pageable).map(ScrapingDataConfigListRecord::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ScrapingDataConfigListRecord> update(@PathVariable Long id, @RequestBody @Valid ScrapingDataConfigTestRecord record, UriComponentsBuilder uriBuilder) {
        var fromDb = service.update(id, record);
        var uri = uriBuilder.path("/scrapingDataConfig/{id}").buildAndExpand(fromDb.getId()).toUri();

        return ResponseEntity.created(uri).body(new ScrapingDataConfigListRecord(fromDb));
    }
}
