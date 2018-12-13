package gamesys.gamesystesttask.rss.storage;

import gamesys.gamesystesttask.rss.RssItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RssItemsStorage {

    @Autowired
    private RssItemRepository rssItemRepository;

    public void save(RssItem rssItem) {
        rssItemRepository.saveAndFlush(rssItem);
    }

    public List<RssItem> getAllItems() {
        return rssItemRepository.getAll();
    }

    public void saveAll(List<RssItem> storedItems) {
        rssItemRepository.saveAll(storedItems);
    }

    public List<RssItem> get10Latest() {
        return rssItemRepository.get10LatestPublishedItems();
    }

    public void clearStorage() {
        rssItemRepository.clear();
    }
}
