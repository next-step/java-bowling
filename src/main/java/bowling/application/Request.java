package bowling.application;

import java.util.Objects;

public class Request {

    private final String name;
    private final int fallenPins;

    public Request(String name, int fallenPins) {
        this.name = name;
        this.fallenPins = fallenPins;
    }

    public int getFallenPins() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return fallenPins == request.fallenPins &&
                Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fallenPins);
    }
}
