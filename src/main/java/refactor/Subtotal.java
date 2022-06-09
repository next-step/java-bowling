package refactor;

import java.util.Objects;

public class Subtotal {
    private State state;
    private int value;

    public Subtotal(State state, int value) {
        this.state = state;
        this.value = value;
    }

    public Subtotal() {
        this.state = State.INIT;
        this.value = 0;
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

    @Override
    public String

    toString() {
        return "Subtotal{" +
                "state=" + state +
                ", value=" + value +
                '}';
    }

    public State state() {
        return this.state;
    }

    public int value() {
        return this.value;
    }

    public void accumulateBonus(int bonus) {
        this.state = this.state.decreaseWait();
        this.value += bonus;
        if (bonus == 10) {
            this.state = State.DONE;
        }
    }
}