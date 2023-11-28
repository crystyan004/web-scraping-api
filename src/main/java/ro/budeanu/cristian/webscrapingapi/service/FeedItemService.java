package ro.budeanu.cristian.webscrapingapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.budeanu.cristian.webscrapingapi.FeedItemRepository;
import ro.budeanu.cristian.webscrapingapi.dto.FeedItemResponse;
import ro.budeanu.cristian.webscrapingapi.dto.PageDTO;
import ro.budeanu.cristian.webscrapingapi.mappers.FeedItemMapper;
import ro.budeanu.cristian.webscrapingapi.model.FeedId;
import ro.budeanu.cristian.webscrapingapi.model.FeedItem;
import ro.budeanu.cristian.webscrapingapi.scarpers.Scarper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FeedItemService {
    private final FeedItemRepository feedItemRepository;
    private final Scarper scarper;

    public void populateDBWithFeedItems(String dataType) {
        try {
            Elements elements = scarper.getScarpingData(dataType);
            List<FeedItem> latestFeedItems = elements.stream()
                    .map(e -> createFeedItemFromHTMLElement(e, dataType))
                    .toList();
            List<FeedItem> currentDBFeedItems = feedItemRepository.findAllByType(dataType);

            feedItemRepository.saveAllAndFlush(getItemFromFirstListNotContainedInSecondList(latestFeedItems, currentDBFeedItems));
            feedItemRepository.deleteAllInBatch(getItemFromFirstListNotContainedInSecondList(currentDBFeedItems, latestFeedItems));
        } catch (Exception e) {
            log.error("Error getting {} data at {} with error {}", dataType, LocalDateTime.now(), e.getMessage());
        }
    }

    public PageDTO<FeedItemResponse> getFeedItemsWithPagination(PageRequest pageable, String dataType) {
        Page<FeedItem> feedItemPage = feedItemRepository.findByTypeWithPagination(dataType, pageable);
        List<FeedItemResponse> feedItemResponseList = feedItemPage.stream()
                .map(FeedItemMapper::mapFeedItemToDTO).toList();

        return new PageDTO<>(
                feedItemResponseList,
                feedItemPage.getTotalPages(),
                feedItemPage.getTotalElements(),
                feedItemPage.isLast(),
                feedItemPage.isFirst()
        );
    }

    public FeedItem createFeedItemFromHTMLElement(Element element, String dataType) {
        final FeedId feedId = FeedId.builder()
                .title(element.select("title").text())
                .type(dataType)
                .creator(element.getElementsByTag("dc:creator").text())
                .build();

        return FeedItem.builder()
                .id(feedId)
                .description(parseDescription(element.select("description").text()))
                .link(element.select("link").text())
                .thumbnail(element.getElementsByTag("media:content").attr("url"))
                .publishDate(element.select("pubDate").text())
                .build();
    }

    public Set<FeedItem> getItemFromFirstListNotContainedInSecondList(List<FeedItem> firstList, List<FeedItem> secondList) {
        return firstList.stream()
                .filter(feedItem -> !secondList.contains(feedItem))
                .collect(Collectors.toSet());
    }

    private String parseDescription(String content) {
        return content != null ? content.replaceAll("<img .*?/>", "") : "";
    }

    public PageDTO<FeedItemResponse> getFeedItemsWithFilterAndPagination(PageRequest pageRequest, String keyword) {
        Page<FeedItem> feedItemPage = feedItemRepository.findFeedItemsWithFilterAndPagination(pageRequest, keyword);
        List<FeedItemResponse> feedItemResponseList = feedItemPage.stream()
                .map(FeedItemMapper::mapFeedItemToDTO).toList();

        return new PageDTO<>(
                feedItemResponseList,
                feedItemPage.getTotalPages(),
                feedItemPage.getTotalElements(),
                feedItemPage.isLast(),
                feedItemPage.isFirst()
        );
    }
}
