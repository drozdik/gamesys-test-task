package gamesys.gamesystesttask.rss;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RssXmlParserTest {

    private RssXmlParser parser = new RssXmlParser();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private String multipleItemsRssXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<rss xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:content=\"http://purl.org/rss/1.0/modules/content/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" version=\"2.0\">\n" +
            "   <channel>\n" +
            "      <title><![CDATA[Lorem ipsum feed for an interval of 30 seconds]]></title>\n" +
            "      <description><![CDATA[This is a constantly updating lorem ipsum feed]]></description>\n" +
            "      <link>http://example.com/</link>\n" +
            "      <generator>RSS for Node</generator>\n" +
            "      <lastBuildDate>Tue, 11 Dec 2018 14:24:00 GMT</lastBuildDate>\n" +
            "      <author><![CDATA[John Smith]]></author>\n" +
            "      <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      <copyright><![CDATA[Michael Bertolacci, licensed under a Creative Commons Attribution 3.0 Unported License.]]></copyright>\n" +
            "      <ttl>30</ttl>\n" +
            "      <item>\n" +
            "         <title><![CDATA[Lorem ipsum 2018-12-11T14:24:00+00:00]]></title>\n" +
            "         <description><![CDATA[Description foo.]]></description>\n" +
            "         <link>http://example.com/test/1544538240</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538240</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "         <title><![CDATA[Lorem ipsum 2018-12-11T14:23:30+00:00]]></title>\n" +
            "         <description><![CDATA[Description bar.]]></description>\n" +
            "         <link>http://example.com/test/1544538210</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538210</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:23:30 GMT</pubDate>\n" +
            "      </item>\n" +
            "   </channel>\n" +
            "</rss>";

    @Test
    public void shouldParseAllItemDescriptions() throws Exception {
        // when
        List<String> allItemDescriptions = parser.parseAllItemDescriptions(multipleItemsRssXml);

        // then
        assertThat(allItemDescriptions, hasItems("Description foo.", "Description bar."));
    }
}