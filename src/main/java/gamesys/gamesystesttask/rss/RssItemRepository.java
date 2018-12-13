package gamesys.gamesystesttask.rss;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class RssItemRepository /*interface extends JpaRepository<RssItem, Long> {

    List<RssItem> findTop10ByOrderByPubDateDesc();*/ {
    @Autowired
    private JdbcTemplate jdbcTemplate;
/*    public int deleteById(long id) {
        return jdbcTemplate.update("delete from student where id=?", new Object[] { id });
    }

    public int insert(Student student) {
    }

    public int update(Student student) {
        return jdbcTemplate.update("update student " + " set name = ?, passport_number = ? " + " where id = ?",
                new Object[] { student.getName(), student.getPassportNumber(), student.getId() });
    }*/

    public void saveAndFlush(RssItem rssItem) {
        try {
            ZonedDateTime timeStampWithZone = rssItem.getPubDate();
//            Timestamp timestamp = new Timestamp(timeStampWithZone.toEpochSecond());
            jdbcTemplate.update("insert into rss_item (id, title, description, pub_date) " + "values(?,  ?, ?, ?)",
                    new Object[]{rssItem.getId(), rssItem.getTitle(), rssItem.getDescription(), timeStampWithZone.toInstant()});

        } catch (DuplicateKeyException e) {
            // skip this record
        }
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

    public List<RssItem> findAll() {
        return jdbcTemplate.query("select * from rss_item", new RssItemRowMapper());
    }

    public void saveAll(List<RssItem> storedItems) {
        storedItems.stream().forEach(item -> saveAndFlush(item));
    }

    public List<RssItem> findTop10ByOrderByPubDateDesc() {
        return jdbcTemplate.query("select * from rss_item order by pub_date desc FETCH FIRST 10 ROWS ONLY", new RssItemRowMapper());
    }
}
