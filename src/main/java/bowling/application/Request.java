package bowling.application;

import bowling.domain.state.Pin;

import java.util.Objects;

public class Request {

    private final String name;
    private Pin fallenPins;

    public Request(String name) {
        this.name = name;
    }

    private Request(String name, Pin fallenPins) {
        this.name = name;
        this.fallenPins = fallenPins;
    }

    public Request bowlFallenPins(Pin fallenPins) {
        return new Request(this.name, fallenPins);
    }

    public Pin getFallenPins() {
        return fallenPins;
    }

    public String getName() {
        return name;
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
