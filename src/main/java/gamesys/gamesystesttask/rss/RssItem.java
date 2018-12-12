package gamesys.gamesystesttask.rss;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // separate entity from pojo?
public class RssItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;

    public RssItem() {
    }

    public RssItem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
