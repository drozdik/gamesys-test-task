package gamesys.gamesystesttask.rss.http;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.storage.RssItemsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Get10LatestRssItemsController {

    @Autowired
    private RssItemsStorage rssItemsStorage;

    @GetMapping("/latest-10-rss-items")
    @ResponseBody
    public List<RssItem> get10LatestItems() {
        return rssItemsStorage.get10Latest();
    }

}
