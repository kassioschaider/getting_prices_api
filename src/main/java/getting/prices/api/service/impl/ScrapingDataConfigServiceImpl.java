package getting.prices.api.service.impl;

import getting.prices.api.domain.price.Price;
import getting.prices.api.domain.price.PriceListRecord;
import getting.prices.api.domain.scrapingdataconfig.*;
import getting.prices.api.domain.sellertag.SellerTag;
import getting.prices.api.domain.sellertag.SellerTagListRecord;
import getting.prices.api.repository.ScrapingDataConfigRepository;
import getting.prices.api.repository.SellerTagRepository;
import getting.prices.api.service.ScrapingDataConfigService;
import getting.prices.api.domain.site.Site;
import getting.prices.api.repository.SiteRepository;
import getting.prices.api.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ScrapingDataConfigServiceImpl implements ScrapingDataConfigService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ScrapingDataConfigRepository scrapingDataConfigRepository;

    @Autowired
    private SellerTagRepository sellerTagRepository;

    @Override
    public ScrapingDataConfigTestResultRecord testConfig(ScrapingDataConfigTestRecord record) {
        var begin = System.currentTimeMillis();

        List<Price> prices = new ArrayList<>();
        List<SellerTag> newSellerTags = new ArrayList<>();
        var site = siteRepository.findById(record.siteId()).orElseThrow();

        try {
            if(ScrapingDataConfigType.PRICE_AND_TAG_SELLER_NAME_BY_ATTRIBUTE_AND_VALUE.equals(record.type())) {
                Document doc = Jsoup.connect(site.getUrl() + record.barCodeProductTest())
                        .userAgent("Mozilla")
                        .timeout(5000)
                        .referrer("http://google.com")
                        .get();

                Elements priceElements = doc.getElementsByClass(record.uniquePriceClass());

                for (Element el : priceElements) {
                    prices.add(
                            new Price(StringUtil.extractPriceValue(el
                                    .getElementsByAttributeValueStarting(record.keyToAttributeElementToGetPrice(), record.attributeValuePrefixToGetPrice())
                                    .text(), record.extractPricePattern()
                            ), el.getElementsByAttributeValueStarting(record.keyToAttributeElementToGetSellerTagName(), record.attributeValuePrefixToGetSellerTagName())
                                    .text()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(ScrapingDataConfigType.PRICE_AND_TAG_SELLER_NAME_BY_ATTRIBUTE_AND_VALUE.equals(record.type()))
            newSellerTags = saveSellerTags(prices, site);

        var end = System.currentTimeMillis();

        BigDecimal avaragePrices = prices.stream()
                .map(Price::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(prices.size()), RoundingMode.DOWN);

        return new ScrapingDataConfigTestResultRecord(
                record,
                end - begin,
                prices.size(),
                avaragePrices,
                prices.stream()
                        .map(PriceListRecord::new)
                        .collect(Collectors.toList()),
                newSellerTags.stream()
                        .map(SellerTagListRecord::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ScrapingDataConfig save(ScrapingDataConfigTestRecord record) {
        Site site = siteRepository.findById(record.siteId()).orElseThrow();
        return scrapingDataConfigRepository.save(new ScrapingDataConfig(record, site));
    }

    @Override
    public Page<ScrapingDataConfig> findAll(Pageable pageable) {
        return scrapingDataConfigRepository.findAll(pageable);
    }

    @Override
    public ScrapingDataConfig getById(Long id) {
        return scrapingDataConfigRepository.findById(id).orElseThrow();
    }

    @Override
    public ScrapingDataConfig update(Long id, ScrapingDataConfigTestRecord record) {
        var site = siteRepository.findById(record.siteId()).orElseThrow();
        var fromDb = scrapingDataConfigRepository.findById(record.siteId()).orElseThrow();
        fromDb.setSite(site);
        fromDb.setType(record.type());
        fromDb.setAttributeValuePrefixToGetPrice(record.attributeValuePrefixToGetPrice());
        fromDb.setKeyToAttributeElementToGetPrice(record.keyToAttributeElementToGetPrice());
        fromDb.setAttributeValuePrefixToGetSellerTagName(record.attributeValuePrefixToGetSellerTagName());
        fromDb.setKeyToAttributeElementToGetSellerTagName(record.keyToAttributeElementToGetSellerTagName());

        return scrapingDataConfigRepository.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        var fromDb = scrapingDataConfigRepository.getReferenceById(id);
        scrapingDataConfigRepository.delete(fromDb);
    }

    private List<SellerTag> saveSellerTags(List<Price> prices, Site site) {
        List<SellerTag> addedSellerTags = new ArrayList<>();

        for (Price p : prices) {
            try {
                addedSellerTags.add(sellerTagRepository.save(new SellerTag(p.getSellerTag(), site)));
            } catch (DataIntegrityViolationException e) {
                System.out.println("Duplicated SellerTag: " + p.getSellerTag());
            }
        }

        return addedSellerTags;
    }
}
