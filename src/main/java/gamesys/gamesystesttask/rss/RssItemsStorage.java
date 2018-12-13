package gamesys.gamesystesttask.rss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RssItemsStorage {

    @Autowired // constructor injection?
    // what if have default constructor with stub implementation and with parameter for injection?
    private RssItemRepository rssItemRepository;

    public void save(RssItem rssItem) {
        rssItemRepository.saveAndFlush(rssItem);
    }

    public List<RssItem> getAllItems() {
        return rssItemRepository.findAll();
    }


    public void saveAll(List<RssItem> storedItems) {
        rssItemRepository.saveAll(storedItems);
    }

    public List<RssItem> get10Latest() {
        return rssItemRepository.findTop10ByOrderByPubDateDesc();
    }

    public void clearStorage() {
        rssItemRepository.clear();
    }
}
