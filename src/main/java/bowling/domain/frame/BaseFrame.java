package bowling.domain.frame;

import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.PitchState;
import bowling.domain.state.PitchStates;
import bowling.domain.state.Start;

public class BaseFrame extends DefaultFrame {

	private static final int MAX_BASE_FRAME_COUNT = 9;

	private Frame nextFrame;
	private PitchState pitchState;

	private BaseFrame(final PitchState pitchState) {
		this.pitchState = pitchState;
		nextFrame = StartingFrame.of();
	}

	@Override
	public Score addScore(final Score score) {
		final Score addedScore = pitchState.addScore(score);

		return nextFrame.addScore(addedScore);
	}

	public static BaseFrame of() {
		return new BaseFrame(Start.of());
	}

	@Override
	public Score getScore() {
		return nextFrame.addScore(pitchState.score());
	}

	@Override
	public void addFrame(final List<Frame> frames) {
		if (pitchState.isFinish()) {
			createNextFrame(frames);
		}
	}

	public void hitPins(final Pins pins) {
		this.pitchState = pitchState.hitPins(pins);
	}

	public PitchStates getPitchStates() {
		return PitchStates.of(pitchState);
	}

	@Override
	public boolean isStart() {
		return pitchState.isStart();
	}

	private void createNextFrame(final List<Frame> frames) {
		this.nextFrame = (frames.size() < MAX_BASE_FRAME_COUNT)
			? BaseFrame.of()
			: LastFrame.of();

		frames.add(nextFrame);
	}
}
