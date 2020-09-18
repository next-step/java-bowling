package bowling.domain.state;

import bowling.domain.DownedPinCount;

import static bowling.domain.DownedPinCount.ALL_PIN_DOWN;

public class InitState implements State {

	public static final String EMPTY = "";
	private static final State INIT = new InitState();

	protected InitState() {}

	public static State getInstance() {
		return INIT;
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
		if(ALL_PIN_DOWN.equals(downedPinCount)) {
			return new Strike(downedPinCount);
		}
		return new Playing(downedPinCount);
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public String reportState() {
		return EMPTY;
	}
}
