package bowling.domain;

import java.util.Objects;

public abstract class Frame {

    protected int number;
    protected Pin firstPin;
    protected Pin secondPin;
    protected int chance = 2;

    protected Frame() {

    }

    public abstract void fitch(int number);

    public abstract boolean isFirst();

    public boolean isStrike() {
        return firstPin.count() == 10;
    }

    public ScoreType score() {
        if (Objects.isNull(firstPin)) {
            throw new IllegalStateException("첫번째 투구가 끝나지 않은 상태입니다.");
        }

        if (firstPin.count() == 10) {
            return ScoreType.STRIKE;
        }

        if (Objects.isNull(secondPin)) {
            throw new IllegalStateException("두번째 투구가 끝나지 않은 상태입니다.");
        }

        if (firstPin.count() + secondPin.count() == 10) {
            return ScoreType.SPARE;
        }

        return ScoreType.MISS;
    }
}
