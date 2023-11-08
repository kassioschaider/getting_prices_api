package getting.prices.api.service.impl;

import getting.prices.api.domain.price.PriceListRecord;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfig;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfigRecord;
import getting.prices.api.domain.scrapingdataconfig.ScrapingDataConfigRepository;
import getting.prices.api.service.ScrapingDataConfigService;
import getting.prices.api.site.Site;
import getting.prices.api.site.SiteRepository;
import getting.prices.api.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ScrapingDataConfigServiceImpl implements ScrapingDataConfigService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ScrapingDataConfigRepository scrapingDataConfigRepository;

    @Override
    public ArrayList<PriceListRecord> testConfig(ScrapingDataConfigRecord record) {
        ArrayList<PriceListRecord> priceListRecords = new ArrayList<>();
        Site site = siteRepository.findById(record.siteId()).orElseThrow();

        try {
            Document doc = Jsoup.connect(site.getUrl() + record.barCodeProductTest())
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("http://google.com")
                    .get();

            Elements priceElements =  doc.getElementsByClass(record.uniquePriceClass());

            for (Element el : priceElements) {
                priceListRecords.add(
                        new PriceListRecord(StringUtil.extractPriceValue(el
                                .getElementsByAttributeValueStarting(record.keyToAttributeElementToGetPrice(), record.attributeValuePrefixToGetPrice())
                                .text(), record.extractPricePattern()
                        ), el.getElementsByAttributeValueStarting(record.keyToAttributeElementToGetSellerTagName(), record.attributeValuePrefixToGetSellerTagName())
                        .text()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return priceListRecords;
    }

    @Override
    public ScrapingDataConfig save(ScrapingDataConfigRecord record) {
        return scrapingDataConfigRepository.save(new ScrapingDataConfig(record));
    }
}
