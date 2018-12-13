package gamesys.gamesystesttask.rss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RssItemRepository extends JpaRepository<RssItem, Long> {

    List<RssItem> findTop10ByOrderByPubDateDesc();
}
