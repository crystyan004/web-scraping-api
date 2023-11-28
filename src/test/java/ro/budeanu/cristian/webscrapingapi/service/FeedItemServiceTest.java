package ro.budeanu.cristian.webscrapingapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.budeanu.cristian.webscrapingapi.FeedItemRepository;
import ro.budeanu.cristian.webscrapingapi.model.FeedId;
import ro.budeanu.cristian.webscrapingapi.model.FeedItem;
import ro.budeanu.cristian.webscrapingapi.scarpers.Scarper;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FeedItemServiceTest {
    @Mock
    private FeedItemRepository feedItemRepository;
    @Mock
    private Scarper scarper;
    private FeedItemService feedItemService;

    @BeforeEach
    void setup() {
        feedItemService = new FeedItemService(feedItemRepository, scarper);
    }

    @Test
    void getItemFromFirstNotContainedInSecondList() {
        //setup
        FeedId sameFeedId = FeedId.builder()
                .creator("Cristian")
                .type("news")
                .title("Cristian news")
                .build();
        FeedItem feedItem1 = FeedItem.builder()
                .id(sameFeedId)
                .link("sssssssss")
                .build();
        FeedItem feedItem2 = FeedItem.builder()
                .id(sameFeedId)
                .link("33333333")
                .build();
        FeedItem feedItem3 = FeedItem.builder()
                .id(FeedId.builder()
                        .creator("Cristian")
                        .type("videos")
                        .title("Cristian videos")
                        .build())
                .link("33333333")
                .build();
        FeedItem feedItem4 = FeedItem.builder()
                .id(FeedId.builder()
                        .creator("Raluca")
                        .type("news")
                        .title("Raluca news")
                        .build())
                .link("33333333")
                .build();

        List<FeedItem> firstList = List.of(feedItem1, feedItem4, feedItem4);
        List<FeedItem> secondList = List.of(feedItem3, feedItem2);

        //execute
        Set<FeedItem> filterdList = feedItemService.getItemFromFirstListNotContainedInSecondList(firstList, secondList);

        //verify
        assertEquals(1, filterdList.size());
    }
}