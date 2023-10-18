package getting.prices.api.controller;

import getting.prices.api.product.Product;
import getting.prices.api.product.ProductListRecord;
import getting.prices.api.product.ProductRecord;
import getting.prices.api.product.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public Product save(@RequestBody @Valid ProductRecord record) {
        return repository.save(new Product(record));
    }

    @GetMapping
    public Page<ProductListRecord> get(@PageableDefault(size = 10, sort = "description") Pageable pageable) {
        return repository.findAll(pageable).map(ProductListRecord::new);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    @Transactional
    public Product update(@PathVariable Long id, @RequestBody @Valid ProductRecord record) {
        Product fromDb = repository.findById(id).orElseThrow();
        fromDb.setBarCode(record.barCode());
        fromDb.setDescription(record.description());
        return repository.save(fromDb);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
