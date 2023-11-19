package ro.budeanu.cristian.webscrapingapi.dto;

import java.util.List;

public record NewsResponse(int count, List<FeedItemResponse> news) {
}
