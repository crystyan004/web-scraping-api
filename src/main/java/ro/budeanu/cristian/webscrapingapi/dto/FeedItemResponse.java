package ro.budeanu.cristian.webscrapingapi.dto;

public record FeedItemResponse(String title, String description, String thumbnail, String link, String publishDate, String type) {
}
