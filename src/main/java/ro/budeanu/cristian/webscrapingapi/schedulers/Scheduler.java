package ro.budeanu.cristian.webscrapingapi.schedulers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.budeanu.cristian.webscrapingapi.FeedItemRepository;
import ro.budeanu.cristian.webscrapingapi.model.FeedItem;
import ro.budeanu.cristian.webscrapingapi.scarpers.Scarper;
import ro.budeanu.cristian.webscrapingapi.service.FeedItemService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ro.budeanu.cristian.webscrapingapi.utils.Constants.NEWS_ITEM;

@Component
@Slf4j
@AllArgsConstructor
public class Scheduler {
    private static final long FEED_TIME = 600_000L;
    private final Scarper scarper;
    private final FeedItemRepository feedItemRepository;
    private FeedItemService feedItemService;

    @Scheduled(fixedDelay = FEED_TIME)
    public void getNewsData() {
        try {
            Elements elements = scarper.getScarpingData(NEWS_ITEM);
            List<FeedItem> latestFeedItems = elements.stream()
                    .map(e -> feedItemService.createFeedItemFromHTMLElement(e, NEWS_ITEM))
                    .collect(Collectors.toList());
            List<FeedItem> currentDBFeedItems = feedItemService.getFeedItemsByType(NEWS_ITEM);

            feedItemRepository.saveAllAndFlush(feedItemService.getItemFromFirstListNotContainedInSecondList(latestFeedItems, currentDBFeedItems));
            feedItemRepository.deleteAllInBatch(feedItemService.getItemFromFirstListNotContainedInSecondList(currentDBFeedItems, latestFeedItems));
        } catch (Exception e) {
            log.error("Error getting feed news data at {} with error {}", LocalDateTime.now(), e.getMessage());
        }
    }
}
