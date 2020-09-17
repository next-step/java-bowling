package bowling.domain.state;

import bowling.domain.pin.Pin;

import java.util.Objects;

public class Spare implements State {

    public static final String SPARE_MARK = "|/";
    private final Pin first;

    private Spare(Pin first) {
        this.first = first;
    }

    public static Spare of(Pin first) {
        return new Spare(first);
    }

    @Override
    public State bowl(Pin pin) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String view() {
        return first + SPARE_MARK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(first, spare.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
