package ro.budeanu.cristian.webscrapingapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.budeanu.cristian.webscrapingapi.dto.FeedItemResponse;
import ro.budeanu.cristian.webscrapingapi.service.FeedItemService;

import java.util.List;

import static ro.budeanu.cristian.webscrapingapi.utils.Constants.NEWS_ITEM;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private final FeedItemService feedItemService;

    @GetMapping()
    ResponseEntity<List<FeedItemResponse>> getNews() {
        return ResponseEntity.ok(feedItemService.getFeedItemResponse(NEWS_ITEM));
    }
}
