package bowling.domain.frame;

import bowling.domain.common.Pins;
import bowling.domain.score.BaseScore;
import bowling.domain.score.Score;
import bowling.domain.state.PitchState;
import bowling.domain.state.PitchStates;
import bowling.domain.state.Start;

public class StartingFrame extends DefaultFrame {

	private PitchState pitchState;

	private StartingFrame(final PitchState pitchState) {
		this.pitchState = pitchState;
	}

	public static StartingFrame of() {
		return new StartingFrame(Start.of());
	}

	@Override
	public Score getScore() {
		return BaseScore.ofZero();
	}

	@Override
	public boolean isStart() {
		return true;
	}

	@Override
	public Score addScore(final Score score) {
		return score;
	}

	@Override
	public void hitPins(final Pins pins) {
		this.pitchState = pitchState.hitPins(pins);
	}

	@Override
	public PitchStates getPitchStates() {
		return PitchStates.of(pitchState);
	}
}
