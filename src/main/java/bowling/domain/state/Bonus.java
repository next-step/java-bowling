package bowling.domain.state;

import bowling.domain.pin.Pins;

public class Bonus implements State {
    private static final int TRY_COUNT_LIMIT = 1;
    private final State preState;
    private final int tryCount;
    private final int fallenPinCount;

    public static Bonus start(final State preState) {
        return new Bonus(preState, 0, 0);
    }

    private Bonus(final State preState, final int tryCount, final int fallenPinCount) {
        if (!(preState instanceof Strike) && !(preState instanceof Spare)) {
            throw new IllegalArgumentException("Bonus는 스트라이크나 스페어일때만 가능합니다.");
        }
        this.preState = preState;
        this.tryCount = tryCount;
        this.fallenPinCount = fallenPinCount;
    }

    @Override
    public boolean isFinish() {
        return tryCount == TRY_COUNT_LIMIT;
    }

    @Override
    public State bowl(final Pins pins) {
        if (isFinish()) {
            throw new IllegalStateException("프레임이 완료되어 볼을 던질 수 없습니다.");
        }
        return new Bonus(preState, tryCount + 1, pins.fallen());
    }

    @Override
    public String print() {
        if (tryCount != TRY_COUNT_LIMIT) {
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
