package gamesys.gamesystesttask.rss.job;

import gamesys.gamesystesttask.rss.RssItemProcessor;
import gamesys.gamesystesttask.rss.RssItemsRetrievingException;
import gamesys.gamesystesttask.rss.RssService;
import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.storage.RssItemsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(GetRssJob.class);

    public void execute() {
        List<RssItem> rssItems = null;
        try {
            rssItems = rssService.getRssItems();
            rssItems.forEach(item -> itemProcessor.prependSimonSays(item));
            itemsStorage.saveAll(rssItems);
        } catch (RssItemsRetrievingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
