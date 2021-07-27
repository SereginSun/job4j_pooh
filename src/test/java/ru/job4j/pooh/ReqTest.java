package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReqTest {
    private static final String LS = System.lineSeparator();

    @Test
    public void whenParseContextThenStoresRequestParameters() {
        String context = "POST /queue/weather HTTP/1.1"
                + LS
                + "Host: localhost:9000"
                + LS
                + "User-Agent: curl/7.55.1"
                + LS
                + "Accept: */*"
                + LS
                + "Content-Length: 7"
                + LS
                + "Content-Type: application/x-www-form-urlencoded"
                + LS + LS
                + "text=13";
        var req = Req.of(context);
        assertThat(req.getMethod(), is("POST"));
        assertThat(req.getMode(), is("queue"));
        assertThat(req.getName(), is("weather"));
        assertThat(req.getText(), is("text=13"));
    }

    @Test
    public void whenParseContextThenStoresRequestParametersWithClientId() {
        String context = "POST /topic/weather/3 HTTP/1.1"
                + LS
                + "Host: localhost:9000"
                + LS
                + "User-Agent: curl/7.55.1"
                + LS
                + "Accept: */*"
                + LS
                + "Content-Length: 7"
                + LS
                + "Content-Type: application/x-www-form-urlencoded"
                + LS + LS
                + "text=13";
        var req = Req.of(context);
        assertThat(req.getMethod(), is("POST"));
        assertThat(req.getMode(), is("topic"));
        assertThat(req.getName(), is("weather"));
        assertThat(req.getId(), is("3"));
        assertThat(req.getText(), is("text=13"));
    }
}