package step4.domain.state;

import java.util.Objects;

public class Spair implements State {
    private int falledPins;

    public Spair() {
        this.falledPins = 10;
    }

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
        Spair spair = (Spair) o;
        return falledPins == spair.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
