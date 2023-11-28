package ro.budeanu.cristian.webscrapingapi.dto;

import java.util.List;

public record ItemsResponse(int count, List<FeedItemResponse> items) {
}
