package gamesys.gamesystesttask.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RssXmlParser {

    public String parseFirstItemDescription(String singleItemRssXml) {
        String itemXml = getFirstItemXml(singleItemRssXml);
        return getDescriptionTextFromItem(itemXml);
    }

    private String getDescriptionTextFromItem(String itemXml) {
        String descriptionText = getDescriptionElementText(itemXml);
        if (descriptionText == null) {
            return "";
        }
        return stripCDATA(descriptionText);
    }

    private String getFirstItemXml(String rssXml) {
        int indexOfItemStart = rssXml.indexOf("<item>");
        int indexOfItemEnd = rssXml.indexOf("</item>");
        return rssXml.substring(indexOfItemStart, indexOfItemEnd + "</item>".length());
    }

    private String getDescriptionElementText(String itemXml) {
        int indexOfDescriptionStart = itemXml.indexOf("<description>");
        if (indexOfDescriptionStart == -1) {
            return null;
        }
        int indexOfDescriptionEnd = itemXml.indexOf("</description>");
        return itemXml.substring(indexOfDescriptionStart + "<description>".length(), indexOfDescriptionEnd);
    }

    private String stripCDATA(String text) {
        text = text.trim();
        if (!text.startsWith("<![CDATA[")) {
            return text;
        }
        text = text.substring(9);
        int i = text.indexOf("]]>");
        if (i == -1) {
            throw new IllegalStateException(
                    "argument starts with <![CDATA[ but cannot find pairing ]]>");
        }
        return text.substring(0, i);
    }

    public List<String> parseAllItemDescriptions(String multipleItemsRssXml) {
        List<String> allItemXmls = getAllItemXmls(multipleItemsRssXml);
        return allItemXmls.stream()
                .map(this::getDescriptionTextFromItem)
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
