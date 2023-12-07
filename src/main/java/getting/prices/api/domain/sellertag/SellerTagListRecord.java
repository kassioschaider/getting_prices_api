package getting.prices.api.domain.sellertag;

public record SellerTagListRecord(String tag) {
    public SellerTagListRecord (SellerTag sellerTag) {
        this(sellerTag.getTag());
    }
}
