package gamesys.gamesystesttask;

import gamesys.gamesystesttask.http.GetBodyFromUrl;
import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.xml.RssXmlParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
/*too high coupling of test with production code? hmm, do i need it?*/
public class RssServiceTest {

    @InjectMocks
    private RssService rssService;

    @Mock
    private RssXmlParser rssXmlParser;

    @Mock
    private GetBodyFromUrl getBodyFromUrl;

    @Before
    public void setUp() throws Exception {
        when(getBodyFromUrl.getResponseBody()).thenReturn(""); // think about it
    }

    @Test
    public void shouldReturnParsedFromRssXmlBodyItems() throws Exception {
        // given
        List<RssItem> parsedItems = Arrays.asList(new RssItem("t1", "d1", ZonedDateTime.now()), new RssItem("t2", "d2", ZonedDateTime.now()));
        when(rssXmlParser.parseAllItems(anyString())).thenReturn(parsedItems);

        // when
        List<RssItem> items = rssService.getRssItems();

        // then
        assertThat(items, contains(parsedItems.toArray()));
    }

    @Test
    public void shouldParseReceivedRssXmlBodyForItems() throws Exception {
        // given
        String rssXml = "rssXml";
        when(getBodyFromUrl.getResponseBody()).thenReturn(rssXml);

        // when
        rssService.getRssItems();

        // then
        verify(rssXmlParser).parseAllItems(rssXml);
    }
}