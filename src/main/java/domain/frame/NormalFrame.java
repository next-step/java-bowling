package domain.frame;

import domain.frame_states.FrameStates;
import domain.frame_states.NormalFrameStates;
import domain.score.Score;
import domain.state.State;
import domain.states.BowlingPins;

import java.util.Optional;

public class NormalFrame implements Frame {

	private final FrameStates frameStates;
	private final Score score;

	private NormalFrame(FrameStates frameStates, Score score) {
		this.frameStates = frameStates;
		this.score = score;
	}

	public static NormalFrame newInstance() {
		return new NormalFrame(NormalFrameStates.newInstance(), Score.of(0, -1));
	}

	@Override
	public void roll(BowlingPins pins) {
		State state = frameStates.roll(pins);
		score.reflect(state);
	}

	@Override
	public void addNextFrameScore(BowlingPins pins) {
		score.reflect(pins);
	}

	@Override
	public void addPreviousScore(int prevScore) {
		score.reflect(prevScore);
	}

	@Override
	public Optional<Integer> getOptionalScore() {
		if (score.isEndCalculation()) {
			return Optional.of(score.getScore());
		}
		return Optional.empty();
	}

	@Override
	public boolean isScoreCalculationEnd() {
		return score.isEndCalculation();
	}

	@Override
	public boolean isEnd() {
		return frameStates.isEndFrame();
	}

}
