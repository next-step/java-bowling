package bowling.domain.state;

import bowling.exception.BowlingGameException;

public abstract class Finished implements State {
    @Override
    public State bowl(int countOfPins) {
        throw new BowlingGameException("한 프레임이 끝난 경우 투구 할 수 없습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
