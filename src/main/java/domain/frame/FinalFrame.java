package domain.frame;

import domain.frame.states.FinalFrameStates;
import domain.frame.states.FrameStates;
import domain.score.Score;
import domain.state.State;
import domain.BowlingPins;
import domain.states.States;

import java.util.Optional;

public class FinalFrame implements Frame {

	private final FrameStates frameStates;
	private Score score;

	private FinalFrame(FrameStates frameStates, Score score) {
		this.frameStates = frameStates;
		this.score = score;
	}

	public static FinalFrame newInstance() {
		return new FinalFrame(FinalFrameStates.newInstance(), Score.of(false));
	}

	@Override
	public void roll(BowlingPins pins) {
		State state = frameStates.roll(pins);
		score = score.reflect(state);
		if (frameStates.isEndFrame()) {
			score = Score.of(score.getScore(), 0, true);
		}
	}

	@Override
	public void addNextFrameScore(BowlingPins pins) {
		score = score.reflect(pins);
	}

	@Override
	public void addPreviousScore(int prevScore) {
		score = score.reflectPrevScore(prevScore);
	}

	@Override
	public Optional<Integer> getOptionalScore() {
		if (score.canShowScore()) {
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

	@Override
	public States getStates() {
		return frameStates.getStates();
	}
}
