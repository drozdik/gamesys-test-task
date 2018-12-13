package gamesys.gamesystesttask.rss;

import gamesys.gamesystesttask.TestDataFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@SpringBootTest // maybe persistence test only?
@RunWith(SpringRunner.class)
public class RssItemsStorageTest {

    @Autowired
    private RssItemsStorage rssItemsStorage;

    @Before
    public void setUp() throws Exception {
        rssItemsStorage.clearStorage();
    }

    @Test
    public void shouldReturnStoredItems() throws Exception {
        // given
        RssItem item = TestDataFactory.createRssItem();

        // when
        rssItemsStorage.save(item);
        List<RssItem> allRssItemDescriptions = rssItemsStorage.getAllItems();

        // then
        assertThat(allRssItemDescriptions, hasItem(item));
    }

    @Test
    public void shouldReturn10LatestItems() throws Exception {
        // given
        List<RssItem> storedItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i == 10) {
                // to separate latest 10, if they have same seconds, doesn't matter, equals takes title and desc as well
                TimeUnit.SECONDS.sleep(1);
            }
            storedItems.add(TestDataFactory.createRssItemPubedNow());
        }

        // when
        rssItemsStorage.saveAll(storedItems);
        List<RssItem> latest = rssItemsStorage.get10Latest();

        // then
        assertThat(latest, containsInAnyOrder(storedItems.subList(10, 20).toArray()));
    }

    @Test
    public void shouldNotSaveDuplicates() throws Exception {
        // given
        List<RssItem> itemsToStore = new ArrayList<>();
        RssItem item1 = TestDataFactory.createRssItem();
        itemsToStore.add(item1);
        RssItem item2 = new RssItem(item1.getTitle(), item1.getDescription(), item1.getPubDate());
        itemsToStore.add(item2);

        // when
        rssItemsStorage.saveAll(itemsToStore);

        // then
        List<RssItem> storedItems = rssItemsStorage.getAllItems();
        item1.setPubDate(item1.getPubDate().truncatedTo(ChronoUnit.SECONDS));
        storedItems.get(0).setPubDate(item1.getPubDate().truncatedTo(ChronoUnit.SECONDS));
        assertThat(storedItems, contains(item1));
    }
}