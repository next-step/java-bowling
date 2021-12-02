package bowling.domain.state;

import bowling.domain.Pins;

public abstract class EndedState implements State {
	@Override
	public State bowl(Pins pins) {
		throw new UnsupportedOperationException(String.format("%s 상태는 투구를 할 수 없습니다.", getClass().getSimpleName()));
	}

	@Override
	public boolean isEnd() {
		return true;
	}
}
