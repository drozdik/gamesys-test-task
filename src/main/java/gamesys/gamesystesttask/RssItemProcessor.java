package gamesys.gamesystesttask;

import gamesys.gamesystesttask.rss.RssItem;
import org.springframework.stereotype.Component;

@Component
public class RssItemProcessor {

    public static final String SIMON_SAYS = "Simon says: ";

    public void prependSimonSays(RssItem item) {
        item.setDescription(SIMON_SAYS + item.getDescription());
    }
}
