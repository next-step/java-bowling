package bowling.entity.score;

import bowling.entity.Pin;
import bowling.entity.Score;

import java.util.Objects;

public class NormalScore extends OnGoing {
    private final Pin pin;

    public NormalScore(int pinValue) {
        this.pin = new Pin(pinValue);
    }

    public NormalScore(Pin pin) {
        this.pin = pin;
    }

    @Override
    public String scoreResult() {
        return String.valueOf(pin.pin());
    }

    @Override
    public Score score() {
        return new Score(pin.pin(), 0);
    }

    @Override
    public Score calculate(Score beforeScore) {

        beforeScore = pin.sumScore(beforeScore);

        if (beforeScore.calculatePossible()) {
            return beforeScore;
        }

        throw new CalculateImPossibleException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
