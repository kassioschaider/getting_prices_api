package getting.prices.api.service;

import getting.prices.api.domain.product.Product;
import getting.prices.api.domain.sellertag.SellerTag;
import getting.prices.api.domain.sellertag.SellerTagUpdateRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerTagService {

    SellerTag save(SellerTag sellerTag);

    Page<SellerTag> findAll(Pageable pageable);

    SellerTag getById(Long id);

    SellerTag update(Long id, SellerTagUpdateRecord data);

    void delete(Long id);
}
