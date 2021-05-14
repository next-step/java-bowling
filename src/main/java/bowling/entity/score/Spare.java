package bowling.entity.score;

import bowling.entity.Pin;
import bowling.entity.Score;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;
import static bowling.entity.Pin.SCORE_ASSOCIATION_SYMBOL;

public class Spare extends Finish {
    private static final String SPARE_SYMBOL = "/";
    private static final int SPARE_SCORE_REMAIN = 1;

    private final Pin firstPin;
    private final int remain;

    public Spare(int firstPinValue) {
        this.firstPin = new Pin(firstPinValue);
        this.remain = SPARE_SCORE_REMAIN;
    }

    public Spare(Pin firstPin, int remain) {
        this.firstPin = firstPin;
        this.remain = remain;
    }

    public Spare(Pin firstPin) {
        this.firstPin = firstPin;
        this.remain = SPARE_SCORE_REMAIN;
    }

    @Override
    public String scoreResult() {
        return firstPin.pin() + SCORE_ASSOCIATION_SYMBOL + SPARE_SYMBOL;
    }

    @Override
    public Score score() {
        return new Score(MAX_PIN_COUNT - firstPin.pin(), remain);
    }

    @Override
    public Score calculate(Score beforeScore) {
        beforeScore = firstPin.sumScore(beforeScore);

        if (beforeScore.calculatePossible()) {
            return beforeScore;
        }

        beforeScore = new Pin(MAX_PIN_COUNT - firstPin.pin()).sumScore(beforeScore);
        return beforeScore;
    }

    @Override
    public Score frameScore() {
        return new Score(MAX_PIN_COUNT, remain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstPin, spare.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
