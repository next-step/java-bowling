package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;

public abstract class EndedState implements ThrowingState {

    @Override
    public ThrowingState bowl(Pins pins) {
        throw new UnsupportedOperationException("프레임이 끝난 상태는 투구할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
