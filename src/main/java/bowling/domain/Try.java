package bowling.domain;

import bowling.exception.ValueNotInRangeException;
import java.util.Objects;
import static bowling.common.SymbolConstants.*;

public class Try {
    public static final Try MAX = new Try(10);
    public static final Try MIN = new Try(0);

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;

    private final int value;

    private Try(final int value) {
        this.value = value;
    }

    public static Try of(final int value) {
        if(value < MIN_VALUE || value > MAX_VALUE) {
            throw new ValueNotInRangeException();
        }

        return new Try(value);
    }

    public Try plus(final Try t) {
        return of(value + t.value);
    }

    public String display() {
        if(MIN.equals(this)) {
            return GUTTER;
        }
        if(MAX.equals(this)) {
            return STRIKE;
        }
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Try)) return false;
        Try t = (Try) o;
        return value == t.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
