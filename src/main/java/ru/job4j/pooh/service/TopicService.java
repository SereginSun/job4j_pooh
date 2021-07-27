package ru.job4j.pooh.service;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class handles topic-mode requests.
 *
 * @author Seregin Vladimir (SereginSun@yandex.ru)
 * @since 26.07.2021
 */
public class TopicService implements Service {
    private static final AtomicInteger ID = new AtomicInteger(0);
    private final ConcurrentMap<String, ConcurrentMap<Integer, ConcurrentLinkedQueue<String>>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        if ("POST".equals(req.getMethod())) {
            ConcurrentMap<Integer, ConcurrentLinkedQueue<String>> uniqueQueue = new ConcurrentHashMap<>();
            uniqueQueue.putIfAbsent(ID.incrementAndGet(), new ConcurrentLinkedQueue<>());
            uniqueQueue.get(ID.get()).add(req.getText());
            queue.putIfAbsent(req.getName(), uniqueQueue);
        } else {
            text = queue.getOrDefault(req.getName(), new ConcurrentHashMap<>()).getOrDefault(
                    Integer.parseInt(req.getId()), new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(text, 200);
    }
}
