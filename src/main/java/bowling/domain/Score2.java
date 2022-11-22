package bowling.domain;

import java.util.Objects;

public class Score2 {

    private final int value;
    private final int left;

    public Score2(int value, int left) {
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

    public Score2 add(PinCount pinCount) {
        return createNext(pinCount);
    }

    private Score2 createNext(PinCount pinCount) {
        return new Score2(value + pinCount.getValue(), left - 1);
    }

    //=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score2 score2 = (Score2) o;
        return value == score2.value && left == score2.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left);
    }

    @Override
    public String toString() {
        return "Score2{" +
                "value=" + value +
                ", left=" + left +
                '}';
    }

    //=================================================================
}

