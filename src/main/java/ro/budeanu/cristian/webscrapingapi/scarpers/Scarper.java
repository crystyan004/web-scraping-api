package ro.budeanu.cristian.webscrapingapi.scarpers;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.budeanu.cristian.webscrapingapi.exceptions.ScarperException;

import java.io.IOException;

import static java.lang.String.format;

@Service
@Slf4j
public class Scarper {
    private final String url;
    private final JsoupService jsoupService;

    public Scarper(@Value("${scheduler.url}") String url, JsoupService jsoupService) {
        this.url = url;
        this.jsoupService = jsoupService;
    }

    public Elements getScarpingData(String dataType) {
        log.info("scheduler started for {} data", dataType);
        try {
            Document document = jsoupService.connect(format("%s/%s", url, dataType));
            Elements elements = document.select("item");
            log.info("scheduler ended for {} data", dataType);
            return elements;
        } catch (IOException e) {
            throw new ScarperException(format("Unable to get %s data with error: %s", dataType, e.getMessage()));
        }
    }
}
