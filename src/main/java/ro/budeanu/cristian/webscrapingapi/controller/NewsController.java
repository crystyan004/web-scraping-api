package ro.budeanu.cristian.webscrapingapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.budeanu.cristian.webscrapingapi.dto.FeedItemResponse;
import ro.budeanu.cristian.webscrapingapi.dto.NewsResponse;
import ro.budeanu.cristian.webscrapingapi.service.FeedItemService;

import java.util.List;

import static ro.budeanu.cristian.webscrapingapi.utils.Constants.NEWS_ITEM;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private final FeedItemService feedItemService;

    @GetMapping()
    ResponseEntity<NewsResponse> getNews() {
        final List<FeedItemResponse> feedItemResponseList = feedItemService.getFeedItemResponse(NEWS_ITEM);
        return ResponseEntity.ok(
                new NewsResponse(feedItemResponseList.size(), feedItemResponseList)
        );
    }
}
