package getting.prices.api.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "barCode")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String barCode;
    private String description;

    public Product(ProductRecord record) {
        this.barCode = record.barCode();
        this.description = record.description();
    }
}
