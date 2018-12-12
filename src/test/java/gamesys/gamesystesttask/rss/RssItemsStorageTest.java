package gamesys.gamesystesttask.rss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

@SpringBootTest // maybe persistence test only?
@RunWith(SpringRunner.class)
public class RssItemsStorageTest {

    @Autowired
    private RssItemsStorage rssItemsStorage;

    @Test
    public void shouldReturnStoredItemDescriptions() throws Exception {
        // given
        String description = "foobar";

        // when
        rssItemsStorage.save(new RssItem(description));
        List<String> allRssItemDescriptions = rssItemsStorage.getAllItemDescriptions();

        // then
        assertThat(allRssItemDescriptions, hasItem(description));
    }
}