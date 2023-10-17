package getting.prices.api.controller;

import getting.prices.api.product.Product;
import getting.prices.api.product.ProductRecord;
import getting.prices.api.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public void save(@RequestBody ProductRecord record) {
        repository.save(new Product(record));
    }

    @GetMapping
    public void get() {

    }

    @PutMapping
    public void update() {

    }

    @DeleteMapping
    public void delete() {

    }
}
