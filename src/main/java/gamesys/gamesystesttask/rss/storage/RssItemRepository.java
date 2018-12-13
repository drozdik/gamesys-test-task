package gamesys.gamesystesttask.rss.storage;

import gamesys.gamesystesttask.rss.RssItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class RssItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveAndFlush(RssItem rssItem) {
        try {
            jdbcTemplate.update("insert into rss_item (id, title, description, pub_date) values(?,  ?, ?, ?)",
                    new Object[]{rssItem.getId(), rssItem.getTitle(), rssItem.getDescription(), rssItem.getPubDate().toInstant()});
        } catch (DuplicateKeyException e) {
            // skip this record
        }
    }

    public void clear() {
        jdbcTemplate.update("DELETE FROM RSS_ITEM");
    }

    static class RssItemRowMapper implements RowMapper<RssItem> {
        @Override
        public RssItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            String title = rs.getString("title");
            String description = rs.getString("description");
            Timestamp pubDate = rs.getTimestamp("pub_date");
            ZonedDateTime dateTime = null;
            if (pubDate != null) {
                Instant instant = pubDate.toInstant();
                dateTime = instant.atZone(ZoneId.of("UTC"));
            }
            return new RssItem(title, description, dateTime);
        }
    }

    public List<RssItem> getAll() {
        return jdbcTemplate.query("select * from rss_item", new RssItemRowMapper());
    }

    // obviously here should be batch update, but can not find good 'insert if not exists' solution right now
    public void saveAll(List<RssItem> storedItems) {
        storedItems.stream().forEach(item -> saveAndFlush(item));
    }

    public List<RssItem> get10LatestPublishedItems() {
        return jdbcTemplate.query("select * from rss_item order by pub_date desc FETCH FIRST 10 ROWS ONLY", new RssItemRowMapper());
    }
}
