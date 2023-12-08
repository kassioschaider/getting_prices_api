package getting.prices.api.service.impl;

import getting.prices.api.domain.sellertag.SellerTag;
import getting.prices.api.domain.sellertag.SellerTagUpdateRecord;
import getting.prices.api.repository.SellerTagRepository;
import getting.prices.api.service.SellerTagService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SellerTagServiceImpl implements SellerTagService {

    @Autowired
    private SellerTagRepository repository;

    @Override
    public SellerTag save(SellerTag sellerTag) {
        return repository.save(sellerTag);
    }

    @Override
    public Page<SellerTag> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public SellerTag getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public SellerTag update(Long id, SellerTagUpdateRecord data) {
        var fromDb = repository.findById(id).orElseThrow();
        if(Strings.isNotBlank(data.description())) fromDb.setDescription(data.description());
        if(Objects.nonNull(data.active())) fromDb.setActive(data.active());

        return repository.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        var fromDb = repository.getReferenceById(id);
        repository.delete(fromDb);
    }
}
