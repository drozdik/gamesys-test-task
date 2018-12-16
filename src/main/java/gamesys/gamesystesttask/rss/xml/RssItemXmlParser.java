package gamesys.gamesystesttask.rss.xml;

import gamesys.gamesystesttask.rss.RssItem;
import gamesys.gamesystesttask.rss.RssXmlParsingException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RssItemXmlParser {

    public RssItem parse(String itemXml) throws RssXmlParsingException {
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
        String elementText = getElementBetweenTags(itemXml, "<pubDate>", "</pubDate>");
        if (elementText == null) {
            return null;
        }
        return elementText;
    }

    private String getTitleTextFromItemElement(String itemXml) throws RssXmlParsingException {
        String elementText = getElementBetweenTags(itemXml, "<title>", "</title>");
        if (elementText == null) {
            return "";
        }
        return stripCDATA(elementText);
    }

    private String getDescriptionTextFromItem(String itemXml) throws RssXmlParsingException {
        String elementText = getElementBetweenTags(itemXml, "<description>", "</description>");
        if (elementText == null) {
            return "";
        }
        return stripCDATA(elementText);
    }

    private String getElementBetweenTags(String itemXml, String startTag, String endTag) {
        int indexOfStart = itemXml.indexOf(startTag);
        if (indexOfStart == -1) {
            return null;
        }
        int indexOfEnd = itemXml.indexOf(endTag);
        return itemXml.substring(indexOfStart + startTag.length(), indexOfEnd);
    }

    private String stripCDATA(String text) throws RssXmlParsingException {
        text = text.trim();
        if (!text.startsWith("<![CDATA[")) {
            return text;
        }
        text = text.substring(9);
        int i = text.indexOf("]]>");
        if (i == -1) {
            throw new RssXmlParsingException(
                    "argument starts with <![CDATA[ but cannot find pairing ]]>");
        }
        return text.substring(0, i);
    }
}
