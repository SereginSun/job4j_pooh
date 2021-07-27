package ru.job4j.pooh.service;

import org.junit.Test;
import ru.job4j.pooh.Req;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueServiceTest {

    @Test
    public void whenServiceProcessWithGetMethod() {
        Service queue = new QueueService();
        var reqPost = new Req("POST", "queue", "weather", "", "text=13");
        var reqGet = new Req("GET", "queue", "weather", "", "text=13");
        queue.process(reqPost);
        var respGet = queue.process(reqGet);
        assertThat(respGet.getText(), is("text=13"));
    }
}