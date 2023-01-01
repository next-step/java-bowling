package bowling.domain.state;

import java.util.Objects;

public class Strike extends Finished {

    public static final String STRIKE_MESSAGE = "X";

    public Strike() {
    }

    @Override
    public String toString() {
        return STRIKE_MESSAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
