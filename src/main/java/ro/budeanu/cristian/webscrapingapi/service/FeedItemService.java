package ro.budeanu.cristian.webscrapingapi.service;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ro.budeanu.cristian.webscrapingapi.FeedItemRepository;
import ro.budeanu.cristian.webscrapingapi.dto.FeedItemResponse;
import ro.budeanu.cristian.webscrapingapi.model.FeedId;
import ro.budeanu.cristian.webscrapingapi.model.FeedItem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedItemService {
    private final FeedItemRepository feedItemRepository;

    public FeedItem createFeedItemFromHTMLElement(Element element, String dataType) {
        final FeedId feedId = FeedId.builder()
                .title(element.select("title").text())
                .type(dataType)
                .creator(element.getElementsByTag("dc:creator").text())
                .build();

        return FeedItem.builder()
                .id(feedId)
                .description(element.select("description").text())
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

    public List<FeedItem> getFeedItemsByType(String dataType) {
        return feedItemRepository.findAllByType(dataType);
    }

    public List<FeedItemResponse> getFeedItemResponse(String dataType) {
        return feedItemRepository.findAllByType(dataType).stream().
                map(feedItem -> new FeedItemResponse(
                        feedItem.getId().getTitle(),
                        feedItem.getDescription(),
                        feedItem.getThumbnail(),
                        feedItem.getLink(),
                        feedItem.getPublishDate()))
                .collect(Collectors.toList());
    }
}
