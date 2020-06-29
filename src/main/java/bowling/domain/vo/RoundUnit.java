package bowling.domain.vo;

import java.util.Objects;

public class RoundUnit {
    public static final RoundUnit ONE = new RoundUnit(1);
    public static final RoundUnit TWO = new RoundUnit(2);
    private final int value;

    public RoundUnit(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RoundUnit roundUnit = (RoundUnit) o;
        return value == roundUnit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
