package gamesys.gamesystesttask;

import gamesys.gamesystesttask.http.GetBodyFromUrl;
import gamesys.gamesystesttask.rss.RssXmlParser;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void shouldReturnParsedFromRssXmlBodyDescriptions() throws Exception {
        // given
        List<String> parsedDescriptions = Arrays.asList("description1", "description2");
        when(rssXmlParser.parseAllItemDescriptions(anyString())).thenReturn(parsedDescriptions);

        // when
        List<String> descriptions = rssService.getRssItemDescriptions();

        // then
        assertThat(descriptions, contains(parsedDescriptions.toArray()));
    }

    @Test
    public void shouldParseReceivedRssXmlBody() throws Exception {
        // given
        String rssXml = "rssXml";
        when(getBodyFromUrl.getResponseBody()).thenReturn(rssXml);

        // when
        rssService.getRssItemDescriptions();

        // then
        verify(rssXmlParser).parseAllItemDescriptions(rssXml);
    }
}