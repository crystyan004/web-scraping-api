package ro.budeanu.cristian.webscrapingapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.budeanu.cristian.webscrapingapi.dto.FeedItemResponse;
import ro.budeanu.cristian.webscrapingapi.dto.PageDTO;
import ro.budeanu.cristian.webscrapingapi.service.FeedItemService;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173/"}, maxAge = 3600)
@RestController
@RequestMapping("/v1/items/")
@AllArgsConstructor
public class ItemsController {
    private final FeedItemService feedItemService;

    @GetMapping("type")
    ResponseEntity<PageDTO<FeedItemResponse>> getItems(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                           @RequestParam(defaultValue = "5") final Integer pageSize,
                                           @RequestParam(defaultValue = "") final String type) {
        return ResponseEntity.ok(feedItemService.getFeedItemsWithPagination(PageRequest.of(pageNumber, pageSize), type));
    }

    @GetMapping("filter")
    public ResponseEntity<PageDTO<FeedItemResponse>> getNewsPageable(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                                                     @RequestParam(defaultValue = "5") final Integer pageSize,
                                                                     @RequestParam(defaultValue = "") final String keyword) {
        return ResponseEntity.ok(feedItemService.getFeedItemsWithFilterAndPagination(PageRequest.of(pageNumber, pageSize), keyword));
    }
}
