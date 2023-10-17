package getting.prices.api.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity(name = "Product")
@Getter
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
