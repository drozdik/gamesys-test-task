package gamesys.gamesystesttask;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.lang.model.util.Types;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.argThat;
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
}
