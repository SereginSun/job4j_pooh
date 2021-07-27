package ru.job4j.pooh.service;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * The class handles queue-mode requests.
 *
 * @author Seregin Vladimir (SereginSun@yandex.ru)
 * @since 26.07.2021
 */
public class QueueService implements Service {
    private final ConcurrentMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        if ("POST".equals(req.getMethod())) {
            queue.putIfAbsent(req.getName(), new ConcurrentLinkedQueue<>());
            queue.get(req.getName()).add(req.getText());
        } else {
            text = queue.getOrDefault(req.getName(), new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(text, 200);
    }
}
