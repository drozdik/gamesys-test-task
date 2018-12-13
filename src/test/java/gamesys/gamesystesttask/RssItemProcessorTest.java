package gamesys.gamesystesttask;

import gamesys.gamesystesttask.rss.RssItem;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.ZonedDateTime;

import static gamesys.gamesystesttask.RssItemProcessor.SIMON_SAYS;
import static org.junit.Assert.*;

public class RssItemProcessorTest {

    private RssItemProcessor processor = new RssItemProcessor();

    @Test
    public void shouldPrependSimonSaysToDescription() throws Exception {
        // given
        RssItem item = new RssItem("title", "description", ZonedDateTime.now());

        // when
        processor.prependSimonSays(item);

        // then
        assertThat(item.getDescription(), Matchers.startsWith(SIMON_SAYS));
    }
}