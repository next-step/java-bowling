package step4.domain.state;

import java.util.Objects;

public class LastBowl implements State {
    private int falledPins;

    public LastBowl(int falledPins) {
        this.falledPins = falledPins;
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
        LastBowl lastBowl = (LastBowl) o;
        return falledPins == lastBowl.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
