package getting.prices.api.service.impl;

import getting.prices.api.scrapingdataconfig.ScrapingDataConfigRecord;
import getting.prices.api.service.ScrapingDataConfigService;
import getting.prices.api.site.Site;
import getting.prices.api.site.SiteRepository;
import getting.prices.api.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


@Service
public class ScrapingDataConfigServiceImpl implements ScrapingDataConfigService {

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public ArrayList<BigDecimal> testConfig(ScrapingDataConfigRecord record) {
        ArrayList<BigDecimal> prices = new ArrayList<>();
        Site site = siteRepository.findById(record.siteId()).orElseThrow();

        try {
            Document doc = Jsoup.connect(site.getUrl() + record.barCodeProductTest()).get();
            record.domPatterns()
                    .forEach(p -> doc.select(p)
                            .forEach(
                                    e -> prices.add(StringUtil.extractPrice(e.text(), record.extractPricePattern()))
                            )
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prices;
    }
}
