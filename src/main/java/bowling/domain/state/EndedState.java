package bowling.domain.state;

import bowling.domain.Pins;

public abstract class EndedState implements ThrowingState {
    @Override
    public ThrowingState bowl(Pins pins) {
        throw new UnsupportedOperationException(String.format("프레임이 끝난 상태(%s)는 투구를 할 수 없습니다.", getClass().getSimpleName()));
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
