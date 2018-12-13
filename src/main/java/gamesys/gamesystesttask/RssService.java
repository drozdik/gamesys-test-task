package gamesys.gamesystesttask;

import gamesys.gamesystesttask.http.GetBodyFromUrl;
import gamesys.gamesystesttask.rss.RssItem;
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
    private GetBodyFromUrl getBodyFromUrl;

    private String multipleItemsRssXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<rss xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:content=\"http://purl.org/rss/1.0/modules/content/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" version=\"2.0\">\n" +
            "   <channel>\n" +
            "      <title><![CDATA[channel title]]></title>\n" +
            "      <description><![CDATA[channel description]]></description>\n" +
            "      <link>http://example.com/</link>\n" +
            "      <generator>RSS for Node</generator>\n" +
            "      <lastBuildDate>Tue, 11 Dec 2018 14:24:00 GMT</lastBuildDate>\n" +
            "      <author><![CDATA[John Smith]]></author>\n" +
            "      <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      <copyright><![CDATA[Michael Bertolacci, licensed under a Creative Commons Attribution 3.0 Unported License.]]></copyright>\n" +
            "      <ttl>30</ttl>\n" +
            "      <item>\n" +
            "         <title><![CDATA[title1]]></title>\n" +
            "         <description><![CDATA[description1]]></description>\n" +
            "         <link>http://example.com/test/1544538240</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538240</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "         <title><![CDATA[title2]]></title>\n" +
            "         <description><![CDATA[description2]]></description>\n" +
            "         <link>http://example.com/test/1544538210</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538210</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:23:30 GMT</pubDate>\n" +
            "      </item>\n" +
            "   </channel>\n" +
            "</rss>";


    public List<RssItem> getRssItems() {
        String rssXml = null;
        try {
            rssXml = getBodyFromUrl.getResponseBody();
        } catch (IOException e) {
            // punch yourself if forget about it
            System.out.println(e);
            rssXml = multipleItemsRssXml;
        }

        return rssXmlParser.parseAllItems(rssXml);
    }
}
