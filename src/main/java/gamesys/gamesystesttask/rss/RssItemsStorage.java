package gamesys.gamesystesttask.rss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class RssItemsStorage {

    @Autowired // constructor injection?
    // what if have default constructor with stub implementation and with parameter for injection?
    private RssItemRepository rssItemRepository;

    public List<String> getAllItemDescriptions() {
        return rssItemRepository.findAll().stream().
                map(RssItem::getDescription)
                .collect(toList());
    }

    public void save(RssItem rssItem) {
        rssItemRepository.saveAndFlush(rssItem);
    }
}
