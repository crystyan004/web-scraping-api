package ro.budeanu.cristian.webscrapingapi.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FeedId implements Serializable {
    private String title;
    private String creator;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedId feedId = (FeedId) o;
        return Objects.equals(title, feedId.title) && Objects.equals(creator, feedId.creator) && Objects.equals(type, feedId.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creator, type);
    }
}
