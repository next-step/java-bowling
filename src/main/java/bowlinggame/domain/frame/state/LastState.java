package bowlinggame.domain.frame.state;

public abstract class LastState implements State {

	protected static final String RESULT_FORMAT = "%s|%s";

	@Override
	public State roll(int pinCount) {
		throw new IllegalStateException("더이상 진행할 수 없습니다.");
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
