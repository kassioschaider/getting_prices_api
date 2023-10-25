package getting.prices.api.controller;

import getting.prices.api.price.PriceListRecord;
import getting.prices.api.scrapingdataconfig.ScrapingDataConfigRecord;
import getting.prices.api.service.ScrapingDataConfigService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("scrapingDataConfig")
public class ScrapingDataConfigController {

    @Autowired
    private ScrapingDataConfigService service;

    @PostMapping("/test")
    public ArrayList<PriceListRecord> testConfig(@RequestBody @Valid ScrapingDataConfigRecord record) {
        return service.testConfig(record);
    }
}
