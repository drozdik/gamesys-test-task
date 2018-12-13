package gamesys.gamesystesttask.rss.http;

import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class HttpRssFeedReaderTest {

    private HttpRssFeedReader httpRssFeedReader = new HttpRssFeedReader();

    @Test
    public void shouldReceiveNonEmptyBody() throws Exception {
        String body = httpRssFeedReader.getRssXmlFromFeed();

        // then
        assertThat(body, not(isEmptyOrNullString()));
    }
}