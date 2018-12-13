package gamesys.gamesystesttask.rss.job;

import gamesys.gamesystesttask.rss.RssItemProcessor;
import gamesys.gamesystesttask.rss.RssService;
import gamesys.gamesystesttask.rss.job.GetRssJob;
import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.storage.RssItemsStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetRssJobTest {

    @InjectMocks
    private GetRssJob getRssJob;

    @Mock
    private RssService rssService;
    @Mock
    private RssItemsStorage itemsStorage;
    @Mock
    private RssItemProcessor itemProcessor;

    @Test
    public void shouldStoreReceivedItems() throws Exception {
        // given
        List<RssItem> receivedItems = Arrays.asList(new RssItem("t1", "d1", ZonedDateTime.now()));
        when(rssService.getRssItems()).thenReturn(receivedItems);

        // when
        getRssJob.execute();

        // then
        ArgumentCaptor<List<RssItem>> captor = ArgumentCaptor.forClass(List.class);
        verify(itemsStorage).saveAll(captor.capture());
        assertThat(captor.getValue(), containsInAnyOrder(receivedItems.toArray()));
    }

    @Test
    public void shouldProcessItems() throws Exception {
        // given
        List<RssItem> receivedItems = Arrays.asList(new RssItem("t1", "d1", ZonedDateTime.now()));
        when(rssService.getRssItems()).thenReturn(receivedItems);

        // when
        getRssJob.execute();

        // then
        verify(itemProcessor, times(receivedItems.size())).prependSimonSays(Mockito.any(RssItem.class));
    }
}
