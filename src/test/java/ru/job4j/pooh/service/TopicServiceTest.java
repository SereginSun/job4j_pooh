package ru.job4j.pooh.service;

import org.junit.Test;
import ru.job4j.pooh.Req;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TopicServiceTest {

    @Test
    public void whenServiceProcessWithGetMethod() {
        Service topic = new TopicService();
        var reqPost = new Req("POST", "topic", "weather", "", "text=13");
        var reqGet = new Req("GET", "topic", "weather", "1", "text=13");
        topic.process(reqPost);
        var respGet = topic.process(reqGet);
        assertThat(respGet.getText(), is("text=13"));
    }
}