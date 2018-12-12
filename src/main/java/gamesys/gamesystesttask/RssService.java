package gamesys.gamesystesttask;

import gamesys.gamesystesttask.http.GetBodyFromUrl;
import gamesys.gamesystesttask.rss.RssXmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class RssService {

    @Autowired
    private RssXmlParser rssXmlParser;

    @Autowired
    private GetBodyFromUrl getBodyFromUrl;

    public List<String> getRssItemDescriptions() { // it can fail to retrieve new items, throw exception
        String rssXml = null;
        try {
            rssXml = getBodyFromUrl.getResponseBody();
        } catch (IOException e) {
            // punch yourself if forget about it
            System.out.println(e);
        }

        List<String> descriptions = rssXmlParser.parseAllItemDescriptions(rssXml);
        return descriptions;
    }
}
