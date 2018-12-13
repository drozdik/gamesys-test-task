package gamesys.gamesystesttask.rss.http;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class HttpRssFeedReader {

    public static final String RSS_FEED_URL = "http://lorem-rss.herokuapp.com/feed?unit=second&interval=30";

    public String getRssXmlFromFeed() throws IOException {
        URL url = new URL(RSS_FEED_URL);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            stringBuffer.append(inputLine);
        in.close();
        return stringBuffer.toString();
    }
}
