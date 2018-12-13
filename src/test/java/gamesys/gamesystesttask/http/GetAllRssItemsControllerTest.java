package gamesys.gamesystesttask.http;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllRssItemsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RssItemsStorage rssItemsStorage;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Ignore("should not create database second time or resolve this problem other way")
    @Test
    public void shouldReturn200WhenSendingRequestToGetItemsEndPoint() throws Exception {
        // when
        ResponseEntity<List> entity = callAllRssItemsEndpoint();

        // then
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

    @SuppressWarnings("rawtypes")
    private ResponseEntity<List> callAllRssItemsEndpoint() {
        String url = "http://localhost:" + port + "/latest-10-rss-items";
        return testRestTemplate.getForEntity(url, List.class);
    }

    @Ignore("should not create database second time or resolve this problem other way")
    @Test
    public void shouldReturnStoredItems() throws Exception {
        // given
        RssItem item1 = new RssItem("t1", "d1", ZonedDateTime.now());
        RssItem item2 = new RssItem("t2", "d2", ZonedDateTime.now());
        rssItemsStorage.save(item1);
        rssItemsStorage.save(item2);

        // when
        ResponseEntity<List> entity = callAllRssItemsEndpoint();

        // then
        List<Map<String, String>> body = entity.getBody();
        assertThat(body, hasItems(
                allOf(hasEntry("title", item1.getTitle()), hasEntry("description", item1.getDescription())/*, hasEntry("pubDate", item1.getPubDate().toString())*/),
                allOf(hasEntry("title", item2.getTitle()), hasEntry("description", item2.getDescription())/*, hasEntry("pubDate", item2.getPubDate().toString())*/)));
    }
}