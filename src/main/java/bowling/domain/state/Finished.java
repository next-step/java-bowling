package bowling.domain.state;

import bowling.exception.BowlingGameException;

public abstract class Finished implements State {
    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State bowl(int pins) {
        throw new BowlingGameException("FINISH 상태에선 bowl 할 수 없습니다.");
    }
}
