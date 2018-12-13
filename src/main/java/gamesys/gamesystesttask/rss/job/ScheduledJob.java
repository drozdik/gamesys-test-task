package gamesys.gamesystesttask.rss.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob {

    private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);

    @Autowired
    private GetRssJob getRssJob;

    @Scheduled(fixedRate = 5000)
    public void executeJobOnSchedule() {
        log.info("Retrieving rss feed");
        getRssJob.execute();
    }
}
