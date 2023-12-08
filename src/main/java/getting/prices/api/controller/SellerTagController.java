package getting.prices.api.controller;

import getting.prices.api.domain.product.Product;
import getting.prices.api.domain.product.ProductListRecord;
import getting.prices.api.domain.product.ProductRecord;
import getting.prices.api.domain.sellertag.SellerTag;
import getting.prices.api.domain.sellertag.SellerTagListRecord;
import getting.prices.api.domain.sellertag.SellerTagRecord;
import getting.prices.api.domain.sellertag.SellerTagUpdateRecord;
import getting.prices.api.service.ProductService;
import getting.prices.api.service.SellerTagService;
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
@RequestMapping("sellerTags")
public class SellerTagController {

    @Autowired
    private SellerTagService service;

    @GetMapping
    public ResponseEntity<Page<SellerTagRecord>> get(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        var page = service.findAll(pageable).map(SellerTagRecord::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerTagRecord> getById(@PathVariable Long id) {
        var fromDb = service.getById(id);
        return ResponseEntity.ok(new SellerTagRecord(fromDb));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SellerTagRecord> update(@PathVariable Long id, @RequestBody @Valid SellerTagUpdateRecord record) {
        return ResponseEntity.ok(new SellerTagRecord(service.update(id, record)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
