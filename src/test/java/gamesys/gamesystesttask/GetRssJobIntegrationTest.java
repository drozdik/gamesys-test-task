package gamesys.gamesystesttask;

import gamesys.gamesystesttask.http.GetBodyFromUrl;
import gamesys.gamesystesttask.rss.RssItemsStorage;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetRssJobIntegrationTest {

    @Autowired
    private GetRssJob getRssJob;
    @Autowired
    private RssItemsStorage rssItemsStorage;

    @Test
    public void shouldGetRssAndStoreThem() throws Exception {
        // given

        // when
        getRssJob.execute();

        // then
        assertThat(rssItemsStorage.getAllItemDescriptions(), not(empty()));

    }
}