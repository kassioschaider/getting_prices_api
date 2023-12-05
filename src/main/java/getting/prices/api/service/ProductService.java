package getting.prices.api.service;

import getting.prices.api.domain.product.Product;
import getting.prices.api.domain.product.ProductRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);

    Product getById(Long id);

    Product update(Long id, Product data);

    void delete(Long id);
}
