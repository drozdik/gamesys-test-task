package gamesys.gamesystesttask.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetBodyFromUrl {

    public String getResponseBody(String url) throws IOException {
        URL yahoo = new URL("http://lorem-rss.herokuapp.com/feed?unit=second&interval=30");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        return null;
    }
}
