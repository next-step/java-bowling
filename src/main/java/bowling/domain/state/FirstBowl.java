package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FirstBowl extends Running {
    private final Pin firstPin;

    public FirstBowl(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public State bowl(Pin pin) {
        if (firstPin.add(pin).isMaxCount()) {
            return new Spare(firstPin, pin);
        }

        return new Miss(firstPin, pin);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return beforeScore.addScoreByPin(firstPin);
    }

    @Override
    public List<Pin> pins() {
        return Arrays.asList(firstPin);
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
        return Objects.equals(firstPin, firstBowl.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
