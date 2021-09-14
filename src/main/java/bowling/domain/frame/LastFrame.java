package bowling.domain.frame;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.PitchState;
import bowling.domain.state.PitchStates;
import bowling.domain.state.Start;

public class LastFrame extends DefaultFrame {

	private static final int MAX_COUNT = 3;

	private PitchStates pitchStates;
	private int count;

	protected LastFrame(final PitchState basePitchState) {
		this.pitchStates = PitchStates.of(basePitchState);
	}

	public static LastFrame of() {
		return new LastFrame(Start.of());
	}

	@Override
	public Score addScore(final Score score) {
		return pitchStates.addScore(score);
	}

	public void hitPins(final Pins pins) {
		count += 1;
		changeLastPitchState(pitchStates.getLast().hitPins(pins));
		addExtraChance();
	}

	@Override
	public boolean isFinish() {
		return isLimitAttemptCount() || isLastStateMiss();
	}

	@Override
	public boolean isStart() {
		if (isFinish()) {
			return true;
		}

		return !pitchStates.getStart().isFinish();
	}

	public PitchStates getPitchStates() {
		return pitchStates;
	}

	@Override
	public Score getScore() {
		return pitchStates.getScore();
	}

	private boolean isLimitAttemptCount() {
		return count == MAX_COUNT;
	}

	private boolean isLastStateMiss() {
		return pitchStates.isLastMiss();
	}

	private void changeLastPitchState(final PitchState pitchState) {
		pitchStates.changeLastPitchState(pitchState);
	}

	private void addExtraChance() {
		if (!this.isFinish() && pitchStates.isLastAllHit()) {
			pitchStates.add(Start.of());
		}
	}
}
