package gamesys.gamesystesttask.http;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
public class GetAllRssItemDescriptionsController {

    @Autowired
    private RssItemsStorage rssItemsStorage;

    @GetMapping("/rss-item-descriptions")
    @ResponseBody
    public List<String> getAllRssItemDescriptions() {
//        rssItemsStorage.save(new RssItem(UUID.randomUUID().toString()));
        return rssItemsStorage.getAllItemDescriptions();
    }

}
