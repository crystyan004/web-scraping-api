package ro.budeanu.cristian.webscrapingapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FeedItem {
    @EmbeddedId
    private FeedId id;
    @Column(length = 4096)
    private String description;
    private String thumbnail;
    private String link;
    private String publishDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedItem feedItem = (FeedItem) o;
        return Objects.equals(id, feedItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
