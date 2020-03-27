package bowling.domain.state;

import java.util.Objects;

public class Miss implements State {
    private int fistFallenPins;
    private int secondFallenPins;

    public Miss(int fistFallenPins, int secondFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
    }

    @Override
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return fistFallenPins == miss.fistFallenPins &&
                secondFallenPins == miss.secondFallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistFallenPins, secondFallenPins);
    }
}
