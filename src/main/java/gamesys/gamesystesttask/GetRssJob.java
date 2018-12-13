package gamesys.gamesystesttask;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRssJob {

    @Autowired
    private RssService rssService;
    @Autowired
    private RssItemsStorage itemsStorage;
    @Autowired
    private RssItemProcessor itemProcessor;


    public void execute() {
        List<RssItem> rssItems = rssService.getRssItems();
        rssItems.forEach(item -> itemProcessor.prependSimonSays(item));
        itemsStorage.saveAll(rssItems);
    }
}
