package getting.prices.api.service.impl;

import getting.prices.api.price.PriceListRecord;
import getting.prices.api.scrapingdataconfig.ScrapingDataConfigRecord;
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

import java.io.IOException;
import java.util.ArrayList;


@Service
public class ScrapingDataConfigServiceImpl implements ScrapingDataConfigService {

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public ArrayList<PriceListRecord> testConfig(ScrapingDataConfigRecord record) {
        ArrayList<PriceListRecord> priceListRecords = new ArrayList<>();
        Site site = siteRepository.findById(record.siteId()).orElseThrow();

        try {
            Document doc = Jsoup.connect(site.getUrl() + record.barCodeProductTest()).get();
            Elements priceElements =  doc.getElementsByClass(record.uniquePriceClass());

            for (Element el : priceElements) {
                priceListRecords.add(
                        new PriceListRecord(StringUtil.extractPriceValue(el
                                .getElementsByAttributeValueStarting("class", "ProductOfferstyles__ProductOfferPrice")
                                .text(), record.extractPricePattern()
                        ), el.getElementsByAttributeValueStarting("class", "ProductOfferstyles__ProductOfferPartnerName")
                        .text()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return priceListRecords;
    }
}
