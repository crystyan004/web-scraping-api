package ro.budeanu.cristian.webscrapingapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.budeanu.cristian.webscrapingapi.model.FeedId;
import ro.budeanu.cristian.webscrapingapi.model.FeedItem;

import java.util.List;

@Repository
public interface FeedItemRepository extends JpaRepository<FeedItem, FeedId> {
    @Query("SELECT feedItem FROM FeedItem feedItem WHERE feedItem.id.type = ?1")
    List<FeedItem> findAllByType(String type);

    @Query("SELECT feedItem FROM FeedItem feedItem WHERE feedItem.id.type = ?1")
    Page<FeedItem> findByTypeWithPagination(String type, Pageable pageable);

    @Query("SELECT feedItem FROM FeedItem feedItem WHERE CONCAT(feedItem.id.title, feedItem.link, feedItem.description, feedItem.publishDate ) LIKE %?1%")
    Page<FeedItem>  findFeedItemsWithFilterAndPagination(PageRequest pageRequest, String keyword);
}
