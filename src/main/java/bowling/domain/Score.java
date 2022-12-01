package bowling.domain;

import java.util.Objects;

public class Score {

    private final int value;
    private final int left;

    public Score(int value, int left) {
        this.value = value;
        this.left = left;
    }

    public int getValue() {
        if (!canCalculate()) {
            throw new IllegalStateException("계산할수 없는 상태입니다 left " + left);
        }

        return value;
    }

    public boolean canCalculate() {
        return left == 0;
    }

    public Score add(PinCount pinCount) {
        return new Score(value + pinCount.getValue(), left - 1);
    }

    //=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value && left == score.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", left=" + left +
                '}';
    }

    //=================================================================
}

