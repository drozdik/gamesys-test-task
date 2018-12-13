package gamesys.gamesystesttask.rss;

import gamesys.gamesystesttask.rss.http.HttpRssFeedReader;
import gamesys.gamesystesttask.rss.xml.RssXmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class RssService {

    @Autowired
    private RssXmlParser rssXmlParser;

    @Autowired
    private HttpRssFeedReader httpRssFeedReader;

    public List<RssItem> getRssItems() {
        String rssXml = null;
        try {
            rssXml = httpRssFeedReader.getRssXmlFromFeed();
        } catch (IOException e) {
            throw new RuntimeException("Cannot retrieve xml body from rss feed", e);
        }

        return rssXmlParser.parseAllItems(rssXml);
    }
}
