package gamesys.gamesystesttask.rss;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class RssItemXmlParserTest {

    private RssItemXmlParser itemParser = new RssItemXmlParser();

    private String rssItemXml =
            "      <item>\n" +
            "         <title><![CDATA[Lorem ipsum 2018-12-11T14:24:00+00:00]]></title>\n" +
            "         <description><![CDATA[Anim duis duis officia veniam reprehenderit laborum nostrud aute.]]></description>\n" +
            "         <link>http://example.com/test/1544538240</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538240</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      </item>\n";

    private String expectedRssItemDescription = "Anim duis duis officia veniam reprehenderit laborum nostrud aute.";
    private String expectedRssItemTitle = "Lorem ipsum 2018-12-11T14:24:00+00:00";
    private ZonedDateTime expectedRssItemPubDate = ZonedDateTime.parse("Tue, 11 Dec 2018 14:24:00 GMT", DateTimeFormatter.RFC_1123_DATE_TIME);

    private String rssItemXmlWithoutChildElements =
            "      <item>\n" +
            "      </item>\n";

    private String rssItemXmlWithBrokenCDATAIinTitle =
            "      <item>\n" +
            "         <title><![CDATA[Lorem ipsum 2018-12-11T14:24:00+00:00]broken]></title>\n" +
            "         <description><![CDATA[This is a constantly updating lorem ipsum feed]]></description>\n" +
            "         <link>http://example.com/test/1544538240</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538240</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      </item>\n";

    private String rssItemXmlWithBrokenCDATAIinDescription =
            "      <item>\n" +
            "         <title><![CDATA[Lorem ipsum 2018-12-11T14:24:00+00:00]broken]></title>\n" +
            "         <description><![CDATA[This is a constantly updating lorem ipsum feed]]></description>\n" +
            "         <link>http://example.com/test/1544538240</link>\n" +
            "         <guid isPermaLink=\"true\">http://example.com/test/1544538240</guid>\n" +
            "         <dc:creator><![CDATA[John Smith]]></dc:creator>\n" +
            "         <pubDate>Tue, 11 Dec 2018 14:24:00 GMT</pubDate>\n" +
            "      </item>\n";




    @Test
    public void shouldParseItemDescription() throws Exception {
        // when
        RssItem item = itemParser.parse(rssItemXml);

        // then
        assertThat(item.getDescription(), is(expectedRssItemDescription));
    }

    @Test
    public void shouldReturnEmptyStringIfDescriptionElementMissing() throws Exception {
        // when
        RssItem item = itemParser.parse(rssItemXmlWithoutChildElements);

        // then
        assertThat(item.getDescription(), is(""));
    }

    @Test
    public void shouldThrowExceptionIfFailedToStripCDATAFromDescription() throws Exception {
        // expect
        expectedException.expect(IllegalStateException.class);

        // when
        itemParser.parse(rssItemXmlWithBrokenCDATAIinDescription);
    }

    @Test
    public void shouldParseItemTitle() throws Exception {
        // when
        RssItem item = itemParser.parse(rssItemXml);

        // then
        assertThat(item.getTitle(), is(expectedRssItemTitle));
    }

    @Test
    public void shouldReturnEmptyStringIfTitleElementMissing() throws Exception {
        // when
        RssItem item = itemParser.parse(rssItemXmlWithoutChildElements);

        // then
        assertThat(item.getTitle(), is(""));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowExceptionIfFailedToStripCDATAFromTitle() throws Exception {
        // expect
        expectedException.expect(IllegalStateException.class);

        // when
        itemParser.parse(rssItemXmlWithBrokenCDATAIinTitle);
    }

    @Test
    public void shouldReadPubDate() throws Exception {
        // when
        RssItem item = itemParser.parse(rssItemXml);

        // then
        assertThat(item.getPubDate(), is(expectedRssItemPubDate));
    }

    @Test
    public void shouldReturnNullIfPubDateElementMissing() throws Exception {
        // when
        RssItem item = itemParser.parse(rssItemXmlWithoutChildElements);

        // then
        assertThat(item.getPubDate(), is(nullValue()));
    }
}