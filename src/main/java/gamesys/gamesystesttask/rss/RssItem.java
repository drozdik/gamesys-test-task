package gamesys.gamesystesttask.rss;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity // separate entity from pojo?
public class RssItem {

    @Id
    private String id;

    private String title;
    private String description;
//    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime pubDate;

    public RssItem() {

    }

    public RssItem(String description) {
        this.description = description;
    }

    public RssItem(String title, String description) {
        this.description = description;
        this.title = title;
    }

    public RssItem(String title, String description, ZonedDateTime pubDate) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        StringBuffer idString = new StringBuffer();
        idString.append(title.substring(0, title.length() >= 50 ? 50 : title.length()));
        idString.append(pubDate == null ? "" : DateTimeFormatter.RFC_1123_DATE_TIME.format(pubDate));
        id =  idString.toString();
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public ZonedDateTime getPubDate() {
        return pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RssItem rssItem = (RssItem) o;

        if (!title.equals(rssItem.title)) return false;
        if (!description.equals(rssItem.description)) return false;
        return pubDate.isEqual(rssItem.pubDate);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + pubDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RssItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pubDate=" + DateTimeFormatter.RFC_1123_DATE_TIME.format(pubDate) +
                '}';
    }
}
