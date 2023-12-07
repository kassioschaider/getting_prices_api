package getting.prices.api.repository;

import getting.prices.api.domain.sellertag.SellerTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerTagRepository extends JpaRepository<SellerTag, Long> {
}
