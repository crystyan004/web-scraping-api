package ro.budeanu.cristian.webscrapingapi.schedulers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.budeanu.cristian.webscrapingapi.service.FeedItemService;

import static ro.budeanu.cristian.webscrapingapi.utils.Constants.NEWS_ITEM;
import static ro.budeanu.cristian.webscrapingapi.utils.Constants.VIDEO_ITEM;

@Component
@Slf4j
@AllArgsConstructor
public class Scheduler {
    private static final long FEED_TIME = 600_000L;
    private FeedItemService feedItemService;

    @Scheduled(fixedDelay = FEED_TIME)
    public void populateNewsData() {
        feedItemService.populateDBWithFeedItems(NEWS_ITEM);
    }

    @Scheduled(fixedDelay = FEED_TIME)
    public void populateVideoData() {
        feedItemService.populateDBWithFeedItems(VIDEO_ITEM);
    }
}
