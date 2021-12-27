package bowling.domain.state;

import bowling.domain.value.Pins;

public abstract class Finished implements State {

    protected static final String BOWL_ERROR_MSG = "투구 할 수 없습니다";
    protected static final String DELIMITER = "|";

    @Override
    public State bowl(Pins pins) {
        throw new IllegalArgumentException(BOWL_ERROR_MSG);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}
