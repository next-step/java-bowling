package bowling.ui;

import bowling.domain.state.Pin;

import java.util.Objects;

public class Request {

    private final String name;
    private Pin pin;

    public Request(String name) {
        this.name = name;
    }

    private Request(String name, Pin pin) {
        this.name = name;
        this.pin = pin;
    }

    public Request bowlFallenPins(Pin pin) {
        return new Request(this.name, pin);
    }

    public Pin getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return pin == request.pin &&
                Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pin);
    }
}
