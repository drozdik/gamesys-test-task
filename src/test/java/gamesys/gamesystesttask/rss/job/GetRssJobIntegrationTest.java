package gamesys.gamesystesttask.rss.job;

import gamesys.gamesystesttask.rss.job.GetRssJob;
import gamesys.gamesystesttask.rss.storage.RssItemsStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertThat(rssItemsStorage.getAllItems(), not(empty()));

    }
}