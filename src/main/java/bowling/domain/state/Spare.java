package bowling.domain.state;

import java.util.Objects;

public class Spare implements State {
    private int fistFallenPins;
    private int secondFallenPins;

    public Spare(int fistFallenPins, int secondFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
    }

    @Override
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return this.fistFallenPins+ "|" +"/";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return fistFallenPins == spare.fistFallenPins &&
                secondFallenPins == spare.secondFallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistFallenPins, secondFallenPins);
    }
}
