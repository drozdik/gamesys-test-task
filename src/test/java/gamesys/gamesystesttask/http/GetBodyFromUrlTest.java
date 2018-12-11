package gamesys.gamesystesttask.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetBodyFromUrlTest {

    private GetBodyFromUrl getBodyFromUrl = new GetBodyFromUrl();

    @Test
    public void name() throws Exception {
        getBodyFromUrl.getResponseBody("");
    }
}