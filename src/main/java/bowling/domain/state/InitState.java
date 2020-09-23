package bowling.domain.state;

import bowling.domain.DownedPinCount;
import bowling.domain.score.GroundScore;
import bowling.domain.score.Score;

import static bowling.domain.DownedPinCount.ALL_PIN_DOWN;

public class InitState implements State {

	public static final String EMPTY = "";
	private static final State INIT = new InitState();

	protected Score score;

	protected InitState() {
		this.score = GroundScore.getInstance();
	}

	public static State getInstance() {
		return INIT;
	}

	@Override
	public State roll(DownedPinCount downedPinCount, Score accumulated) {
		if(ALL_PIN_DOWN.equals(downedPinCount)) {
			return new Strike(downedPinCount, accumulated);
		}
		return new Playing(downedPinCount, accumulated);
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public String reportState() {
		return EMPTY;
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public void addPreviousCount(DownedPinCount pinCount) {
		score.addExtraCount(pinCount);
	}
}
