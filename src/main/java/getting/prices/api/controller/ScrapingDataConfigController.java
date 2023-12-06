package getting.prices.api.controller;

import getting.prices.api.domain.price.PriceListRecord;
import getting.prices.api.domain.product.ProductListRecord;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfigRecord;
import getting.prices.api.service.ScrapingDataConfigService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("scrapingDataConfig")
public class ScrapingDataConfigController {

    @Autowired
    private ScrapingDataConfigService service;

    @PostMapping("/test")
    public ResponseEntity<List<PriceListRecord>> testConfig(@RequestBody @Valid ScrapingDataConfigRecord record) {
        return ResponseEntity.ok(service.testConfig(record));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ScrapingDataConfig> save(@RequestBody @Valid ScrapingDataConfigRecord record, UriComponentsBuilder uriBuilder) {
        var fromDb = service.save(record);
        var uri = uriBuilder.path("/scrapingDataConfig/{id}").buildAndExpand(fromDb.getId()).toUri();

        return ResponseEntity.created(uri).body(fromDb);
    }
}
