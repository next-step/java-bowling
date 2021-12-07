package bowling.domain.state;

import bowling.domain.value.Pins;

public abstract class FinishState implements State {
    protected static final String DELIMITER = "|";
    protected static final int MAXIMUM_COUNT = 10;
    private static final String INVALID_PITCH = "투구할 수 없습니다.";

    @Override
    public State pitch(Pins pins) {
        throw new IllegalArgumentException(INVALID_PITCH);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}
