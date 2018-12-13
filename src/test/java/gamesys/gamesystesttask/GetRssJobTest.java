package gamesys.gamesystesttask;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        ArgumentCaptor<RssItem> captor = ArgumentCaptor.forClass(RssItem.class);
        verify(itemsStorage).save(captor.capture());
        assertThat(captor.getValue(), is(receivedItems.get(0)));
    }
}
