package bowling_step3.domain;

import java.util.Objects;

public class Subtotal {
    private State state;
    private int value;

    public Subtotal() {
        this.state = State.INIT;
        this.value = 0;
    }

    public State state() {
        return this.state;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Subtotal{" +
                "state=" + state +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtotal subtotal = (Subtotal) o;
        return value == subtotal.value && state == subtotal.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, value);
    }
}
