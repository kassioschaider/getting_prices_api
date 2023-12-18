package getting.prices.api.repository;

import getting.prices.api.domain.sellertag.SellerTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerTagRepository extends JpaRepository<SellerTag, Long> {
    Optional<SellerTag> findByTagAndSiteId(String tag, Long siteId);
}
