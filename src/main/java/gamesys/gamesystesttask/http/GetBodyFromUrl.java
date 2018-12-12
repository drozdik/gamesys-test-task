package gamesys.gamesystesttask.http;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class GetBodyFromUrl {

    public String getResponseBody() throws IOException {
        URL yahoo = new URL("http://lorem-rss.herokuapp.com/feed?unit=second&interval=30");
        URLConnection yc = yahoo.openConnection();
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
