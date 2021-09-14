package bowling.domain.frame;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.PitchState;
import bowling.domain.state.PitchStates;
import bowling.domain.state.Start;

public class BaseFrame extends DefaultFrame {

	private PitchState pitchState;
	private Frame nextFrame;

	public BaseFrame(final PitchState pitchState, final Frame nextFrame) {
		this.pitchState = pitchState;
		this.nextFrame = nextFrame;
	}

	public static BaseFrame of(final PitchState pitchState, final Frame nextFrame) {
		return new BaseFrame(pitchState, nextFrame);
	}

	public static BaseFrame of() {
		return new BaseFrame(Start.of(), StartingFrame.of());
	}

	public BaseFrame create(final Frame nextFrame) {
		return new BaseFrame(pitchState, nextFrame);
	}

	@Override
	public Score addScore(final Score score) {
		final Score addedScore = pitchState.addScore(score);

		return nextFrame.addScore(addedScore);
	}

	public void updateNext(final Frame nextFrame) {
		this.nextFrame = nextFrame;
	}

	public void hitPins(final Pins pins) {
		this.pitchState = pitchState.hitPins(pins);
	}

	@Override
	public Score getScore() {
		return nextFrame.addScore(pitchState.score());
	}

	public PitchStates getPitchStates() {
		return PitchStates.of(pitchState);
	}

	@Override
	public boolean isFinish() {
		return pitchState.isFinish();
	}

	@Override
	public boolean isStart() {
		return !pitchState.isFinish();
	}
}
