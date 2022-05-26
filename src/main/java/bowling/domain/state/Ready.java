package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Ready extends Running {
    @Override
    public State bowl(int fallPins) {
        Pins pins = new Pins(fallPins);
        if (pins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pins);
    }

    @Override
    public boolean canCalculate(Score score) {
        return false;
    }

    @Override
    public Score score() {
        throw new IllegalArgumentException();
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "      |";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ready;
    }
}
