package gamesys.gamesystesttask.rss;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity // separate entity from pojo?
public class RssItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
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

}
