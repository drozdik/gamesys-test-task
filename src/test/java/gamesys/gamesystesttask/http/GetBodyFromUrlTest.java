package gamesys.gamesystesttask.http;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class GetBodyFromUrlTest {

    private GetBodyFromUrl getBodyFromUrl = new GetBodyFromUrl();

    @Test
    public void shouldReceiveNonEmptyBody() throws Exception {
        String body = getBodyFromUrl.getResponseBody();

        // then
        assertThat(body, not(isEmptyOrNullString()));
    }
}