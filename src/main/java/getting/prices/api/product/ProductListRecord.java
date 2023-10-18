package getting.prices.api.product;

public record ProductListRecord(Long id, String barCode, String description) {

    public ProductListRecord(Product product) {
        this(product.getId(), product.getBarCode(), product.getDescription());
    }
}
