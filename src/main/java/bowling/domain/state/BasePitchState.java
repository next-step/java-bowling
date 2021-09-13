package bowling.domain.state;

import bowling.domain.common.Pins;
import bowling.domain.score.BaseScore;
import bowling.domain.score.Score;
import bowling.domain.state.exception.InvalidProgressPitchException;

public abstract class BasePitchState implements PitchState {

	protected static final int MAX_HIT_PINS_COUNT = 10;

	public PitchState hitPins(final Pins pins) {
		throw new InvalidProgressPitchException();
	}

	public Score score() {
		return BaseScore.ofZero();
	}

	public Score addScore(final Score score) {
		if (score.isComputeAble()) {
			return score;
		}

		return addBonusScore(score);
	}

	public boolean isProgress() {
		return false;
	}

	public Score addBonusScore(final Score score) {
		return score;
	}
}
