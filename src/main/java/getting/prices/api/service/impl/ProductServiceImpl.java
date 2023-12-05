package getting.prices.api.service.impl;

import getting.prices.api.domain.product.Product;
import getting.prices.api.repository.ProductRepository;
import getting.prices.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Product update(Long id, Product data) {
        var fromDb = repository.getReferenceById(id);
        fromDb.setBarCode(data.getBarCode());
        fromDb.setDescription(data.getDescription());

        return repository.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        var fromDb = repository.getReferenceById(id);
        repository.delete(fromDb);
    }
}
