package bowling.domain.state;

import bowling.domain.PinCount;

public abstract class Finished implements State {
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State play(final PinCount pinCount) {
        throw new IllegalStateException("플레이가 종료 되었습니다.");
    }
}
