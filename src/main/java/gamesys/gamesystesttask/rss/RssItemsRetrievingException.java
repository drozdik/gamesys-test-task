package gamesys.gamesystesttask.rss;

public class RssItemsRetrievingException extends Exception {

    public RssItemsRetrievingException(Exception e) {
        super("Could not retrieve rss items", e);
    }
}
