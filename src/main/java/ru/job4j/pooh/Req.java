package ru.job4j.pooh;

/**
 * The Req class parses and stores request parameters and provides methods to access them.
 *
 * @author Seregin Vladimir (SereginSun@yandex.ru)
 * @since 26.07.2021
 */
public class Req {
    private final String method;
    private final String mode;
    private final String name;
    private final String id;
    private final String text;

    public Req(String method, String mode, String name, String id, String text) {
        this.method = method;
        this.mode = mode;
        this.name = name;
        this.id = id;
        this.text = text;
    }

    public static Req of(String content) {
        String[] elements = content.split("\\s");
        String method = elements[0];
        String[] values = elements[1].substring(1).split("/");
        String mode = values[0];
        String name = values[1];
        String id = "";
        if (values.length > 2) {
            id = values[2];
        }
        String text = elements[elements.length - 1];
        return new Req(method, mode, name, id, text);
    }

    public String getMethod() {
        return method;
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
