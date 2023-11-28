package ro.budeanu.cristian.webscrapingapi.mappers;

import lombok.experimental.UtilityClass;
import ro.budeanu.cristian.webscrapingapi.dto.FeedItemResponse;
import ro.budeanu.cristian.webscrapingapi.model.FeedItem;

@UtilityClass
public class FeedItemMapper {
    public static FeedItemResponse mapFeedItemToDTO(FeedItem feedItem) {
        return new FeedItemResponse(
                feedItem.getId().getTitle(),
                feedItem.getDescription(),
                feedItem.getThumbnail(),
                feedItem.getLink(),
                feedItem.getPublishDate(),
                feedItem.getId().getType()
        );
    }
}
