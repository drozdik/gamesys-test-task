package gamesys.gamesystesttask.http;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllRssItemDescriptionsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RssItemsStorage rssItemsStorage;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToController() throws Exception {
        // when
        ResponseEntity<List> entity = callAllRssItemDescriptionsEndpoint();

        // then
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

    @SuppressWarnings("rawtypes")
    private ResponseEntity<List> callAllRssItemDescriptionsEndpoint() {
        String url = "http://localhost:" + port + "/rss-item-descriptions";
        return testRestTemplate.getForEntity(url, List.class);
    }

    @Test
    public void shouldReturnStoredItemDescriptions() throws Exception {
        // given
        String description1 = UUID.randomUUID().toString();
        rssItemsStorage.save(new RssItem(description1));

        String description2 = UUID.randomUUID().toString();
        rssItemsStorage.save(new RssItem(description2));

        // when
        ResponseEntity<List> entity = callAllRssItemDescriptionsEndpoint();

        // then
        List<String> body = entity.getBody();
        assertThat(body, hasItems(description1, description2));
    }
}