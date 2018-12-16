package gamesys.gamesystesttask.rss.xml;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssXmlParsingException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RssXmlParser {

    private RssItemXmlParser rssItemXmlParser = new RssItemXmlParser();

    public List<RssItem> parseAllItems(String rssXml) throws RssXmlParsingException{
        List<RssItem> items = new ArrayList<>();
        List<String> allItemXmls = getAllItemXmls(rssXml);
        for (String itemXml : allItemXmls) {
            items.add(rssItemXmlParser.parse(itemXml));
        }
        return items;
    }

    private List<String> getAllItemXmls(String rssXml) throws RssXmlParsingException {
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
            if(indexOfItemClosing == -1){
                throw new RssXmlParsingException("Cannot find valid closing tag for 'item' element");
            }
            indexOfItemEnd = indexOfItemClosing + "</item>".length();
            items.add(rssXml.substring(indexOfItemOpening, indexOfItemEnd));
        }
        return items;
    }
}
