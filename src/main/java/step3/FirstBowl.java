package step3;

import java.util.Objects;
import step3.state.Miss;
import step3.state.Spair;
import step3.state.State;

public class FirstBowl implements State {
    private final int pins;

    public FirstBowl(int firstFalledPins) {
        pins = firstFalledPins;
    }

    public State bowl(int secondFalledPins) {
        if (pins + secondFalledPins == 10) {
            return new Spair();
        }
        return new Miss(pins, secondFalledPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstBowl firstBowl = (FirstBowl) o;
        return pins == firstBowl.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
