package gamesys.gamesystesttask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledJob {

    private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);

    @Autowired
    private GetRssJob getRssJob;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("Retrieving rss feed");
        getRssJob.execute();
    }
}
