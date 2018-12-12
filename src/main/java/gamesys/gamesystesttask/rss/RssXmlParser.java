package gamesys.gamesystesttask.rss;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RssXmlParser {

    private RssItemXmlParser rssItemXmlParser = new RssItemXmlParser();

    public List<String> parseAllItemDescriptions(String multipleItemsRssXml) {
        List<String> allItemXmls = getAllItemXmls(multipleItemsRssXml);
        return allItemXmls.stream()
                .map(itemXml -> rssItemXmlParser.parse(itemXml).getDescription())
                .collect(Collectors.toList());
    }

    private List<String> getAllItemXmls(String rssXml) {
        int indexOfItemOpening = 0;
        int indexOfItemClosing = 0;
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
