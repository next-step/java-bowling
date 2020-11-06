package bowling.domain.state;

import bowling.domain.pin.Pins;

public class Bonus implements State {
    public static final int DEFAULT_COUNT = 1;
    private final State preState;
    private final int leftCount;
    private final int fallenPinCount;

    public static Bonus start(final State preState) {
        return new Bonus(preState, DEFAULT_COUNT, 0);
    }

    private Bonus(final State preState, final int leftCount, final int fallenPinCount) {
        if (!(preState instanceof Strike) && !(preState instanceof Spare) && !(preState instanceof Bonus)) {
            throw new IllegalArgumentException("Bonus는 스트라이크나 스페어일때만 가능합니다.");
        }
        this.preState = preState;
        this.leftCount = leftCount;
        this.fallenPinCount = fallenPinCount;
    }

    @Override
    public boolean isFinish() {
        return leftCount == 0;
    }

    @Override
    public State bowl(final Pins pins) {
        if (isFinish()) {
            throw new IllegalStateException("프레임이 완료되어 볼을 던질 수 없습니다.");
        }

        if (pins.count() == 10 && (preState instanceof Strike)) {
            Bonus bonus = new Bonus(preState, leftCount - 1, pins.count());
            return new Bonus(bonus, DEFAULT_COUNT, 0);
        }

        return new Bonus(preState, leftCount - 1, pins.count());
    }

    @Override
    public String print() {
        if (leftCount > 0) {
            return preState.print();
        }

        if (fallenPinCount == 10) {
            return preState.print() + "|X";
        }

        if (fallenPinCount == 0) {
            return preState.print() + "|-";
        }

        return preState.print() + "|" + fallenPinCount;
    }
}
