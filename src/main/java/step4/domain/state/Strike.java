package step4.domain.state;

import java.util.Objects;

public class Strike implements State{
    private int falledPins;

    public Strike() {
        this.falledPins = 10;
    }

    @Override
    public State throwBowl(int falledPins) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Strike strike = (Strike) o;
        return falledPins == strike.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
