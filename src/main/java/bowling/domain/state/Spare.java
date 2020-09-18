package bowling.domain.state;

import bowling.domain.pin.Pin;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.Objects;

public class Spare implements State {

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
    public String makeSymbol() {
        return first + Symbol.SPARE.toString();
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
