package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Objects;

public class Miss extends Finished {

    public static final String MISS_MESSAGE = "|";

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public Score score() {
        return Score.ofMiss(firstPin.amount() + secondPin.amount());
    }

    @Override
    public Score calculateScore(Score lastScore) {
        return lastScore
                .bowl(firstPin.amount())
                .bowl(secondPin.amount());
    }

    @Override
    public String toString() {
        return firstPin + MISS_MESSAGE + secondPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
