package domain.frame;

import domain.frame_states.FinalFrameStates;
import domain.frame_states.FrameStates;
import domain.score.Score;
import domain.state.State;
import domain.states.BowlingPins;

import java.util.Optional;

public class FinalFrame implements Frame {

	private final FrameStates frameStates;
	private Score score;

	private FinalFrame(FrameStates frameStates, Score score) {
		this.frameStates = frameStates;
		this.score = score;
	}

	public static FinalFrame newInstance() {
		return new FinalFrame(FinalFrameStates.newInstance(), Score.of(0, -1));
	}

	@Override
	public void roll(BowlingPins pins) {
		State state = frameStates.roll(pins);
		score.reflect(state);
		if (frameStates.isEndFrame()) {
			score = Score.of(score.getScore(), 0);
		}
	}

	@Override
	public Optional<Integer> getOptionalScore() {
		if (score.canCalculateScore()) {
			return Optional.of(score.getScore());
		}
		return Optional.empty();
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public void addNextFrameScore(BowlingPins pins) {
		throw new UnsupportedOperationException("마지막 프레임에서는 다른 프레임으로부터 점수를 더할 수 없습니다");
	}

	@Override
	public void addPreviousScore(Score prevScore) {
		score.reflect(prevScore);
	}

	@Override
	public boolean isEnd() {
		return frameStates.isEndFrame();
	}

}
