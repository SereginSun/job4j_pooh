package ru.job4j.pooh;

/**
 * The Resp class stores response parameters and provides methods to access them.
 *
 * @author Seregin Vladimir (SereginSun@yandex.ru)
 * @since 26.07.2021
 */
public class Resp {
    private final String text;
    private final int status;

    public Resp(String text, int status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public int getStatus() {
        return status;
    }
}
