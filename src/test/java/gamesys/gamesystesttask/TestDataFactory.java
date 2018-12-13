package gamesys.gamesystesttask;

import gamesys.gamesystesttask.rss.RssItem;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataFactory {

    public static RssItem createRssItem() {
        String title = "title " + UUID.randomUUID().toString();
        String description = "description " + UUID.randomUUID().toString();
        ZonedDateTime pubDate = ZonedDateTime.now().plusDays(ThreadLocalRandom.current().nextInt(0, 300 + 1)).truncatedTo(ChronoUnit.SECONDS);
        return new RssItem(title, description, pubDate);
    }

    public static RssItem createRssItemPubedNow() {
        String title = "title " + UUID.randomUUID().toString();
        String description = "description " + UUID.randomUUID().toString();
        ZonedDateTime pubDate = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        return new RssItem(title, description, pubDate);
    }
}
