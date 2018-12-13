package gamesys.gamesystesttask.rss.xml;

import gamesys.gamesystesttask.rss.RssItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RssXmlParser {

    private RssItemXmlParser rssItemXmlParser = new RssItemXmlParser();

    public List<RssItem> parseAllItems(String rssXml) {
        return getAllItemXmls(rssXml).stream()
                .map(itemXml -> rssItemXmlParser.parse(itemXml))
                .collect(toList());
    }

    private List<String> getAllItemXmls(String rssXml) {
        int indexOfItemOpening;
        int indexOfItemClosing;
        int indexOfItemEnd = 0;
        List<String> items = new ArrayList<>();

        while (true) {
            indexOfItemOpening = rssXml.indexOf("<item>", indexOfItemEnd);
            if (indexOfItemOpening == -1) {
                break;
            }
            indexOfItemClosing = rssXml.indexOf("</item>", indexOfItemEnd);
            indexOfItemEnd = indexOfItemClosing + "</item>".length();
            items.add(rssXml.substring(indexOfItemOpening, indexOfItemEnd));
        }
        return items;
    }
}
