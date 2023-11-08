package getting.prices.api.controller;

import getting.prices.api.domain.product.Product;
import getting.prices.api.domain.product.ProductListRecord;
import getting.prices.api.domain.product.ProductRecord;
import getting.prices.api.domain.product.ProductRepository;
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
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductListRecord> save(@RequestBody @Valid ProductRecord record, UriComponentsBuilder uriBuilder) {
        var fromDb = repository.save(new Product(record));
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(fromDb.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductListRecord(fromDb));
    }

    @GetMapping
    public ResponseEntity<Page<ProductListRecord>> get(@PageableDefault(size = 10, sort = "description") Pageable pageable) {
        var page = repository.findAll(pageable).map(ProductListRecord::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductListRecord> getById(@PathVariable Long id) {
        var fromDb = repository.getReferenceById(id);
        return ResponseEntity.ok(new ProductListRecord(fromDb));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductListRecord> update(@PathVariable Long id, @RequestBody @Valid ProductRecord record) {
        var fromDb = repository.getReferenceById(id);
        fromDb.setBarCode(record.barCode());
        fromDb.setDescription(record.description());

        return ResponseEntity.ok(new ProductListRecord(fromDb));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var fromDb = repository.getReferenceById(id);
        repository.delete(fromDb);

        return ResponseEntity.noContent().build();
    }
}
