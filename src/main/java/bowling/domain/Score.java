package bowling.domain;

import java.util.Objects;
import java.util.Optional;

public class Score {

    public static final int MAX = 300;
    public static final int MIN = 0;
    private final int value;

    public Score(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("점수는 0 ~ 300 사이 입니다. " + value);
        }
        this.value = value;
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public static Score of(PinCount pinCount) {
        return Score.of(pinCount.getValue());
    }

    public static Score of(RollingResult rollingResult) {
        Result result = rollingResult.getResult();
        Score current = Score.of(rollingResult.getPinCount());

        if (result.isStrike()) {
            return current
                    .add(rollingResult.getAfterPinCount())
                    .add(rollingResult.getAfterAfterPinCount());
        }

        if (result.isSpare()) {
            return current.add(rollingResult.getAfterPinCount());
        }

        return current;
    }

    public Score add(Score score) {
        return Score.of(value + score.value);
    }

    public Score add(PinCount pinCount) {
        return Score.of(value + pinCount.getValue());
    }

    // =======================================================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "";
    }
}
