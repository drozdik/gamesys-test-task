package gamesys.gamesystesttask.rss.xml;

import gamesys.gamesystesttask.rss.RssItem;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RssItemXmlParser {

    public RssItem parse(String itemXml) {
        String description = getDescriptionTextFromItem(itemXml);
        String title = getTitleTextFromItemElement(itemXml);
        String pubDateString = getPubDateTextFromItemElement(itemXml);
        ZonedDateTime pubDate = null;
        if (pubDateString != null) {
            pubDate = ZonedDateTime.parse(pubDateString, DateTimeFormatter.RFC_1123_DATE_TIME);
        }
        return new RssItem(title, description, pubDate);
    }

    private String getPubDateTextFromItemElement(String itemXml) {
        String titleText = getPubDateElementText(itemXml);
        if (titleText == null) {
            return null;
        }
        return titleText;
    }

    private String getPubDateElementText(String itemXml) {
        int indexOfDescriptionStart = itemXml.indexOf("<pubDate>");
        if (indexOfDescriptionStart == -1) {
            return null;
        }
        int indexOfDescriptionEnd = itemXml.indexOf("</pubDate>");
        return itemXml.substring(indexOfDescriptionStart + "<pubDate>".length(), indexOfDescriptionEnd);
    }

    private String getTitleTextFromItemElement(String itemXml) {
        String titleText = getTitleElementText(itemXml);
        if (titleText == null) {
            return "";
        }
        return stripCDATA(titleText);
    }

    private String getTitleElementText(String itemXml) {
        int indexOfDescriptionStart = itemXml.indexOf("<title>");
        if (indexOfDescriptionStart == -1) {
            return null;
        }
        int indexOfDescriptionEnd = itemXml.indexOf("</title>");
        return itemXml.substring(indexOfDescriptionStart + "<title>".length(), indexOfDescriptionEnd);
    }

    private String getDescriptionTextFromItem(String itemXml) {
        String descriptionText = getDescriptionElementText(itemXml);
        if (descriptionText == null) {
            return "";
        }
        return stripCDATA(descriptionText);
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
}
