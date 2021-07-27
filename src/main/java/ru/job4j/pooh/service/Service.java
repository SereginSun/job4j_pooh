package ru.job4j.pooh.service;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

/**
 * Service for processing requests.
 *
 * @author Seregin Vladimir (SereginSun@yandex.ru)
 * @since 26.07.2021
 */
public interface Service {
    Resp process(Req req);
}
