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
		return new NormalFrame(NormalFrameStates.newInstance(), Score.of(0));
	}

	@Override
	public void roll(BowlingPins pins) {
		State state = frameStates.roll(pins);
		score.reflect(state);
	}

	@Override
	public void initializeScore(Score score) {
		score.initialize(score.getScore());
	}

	@Override
	public Optional<Integer> getScore() {
		if (score.canCalculateScore()) {
			return Optional.of(score.getScore());
		}
		return Optional.empty();
	}

	@Override
	public void addScore(BowlingPins pins) {
		score.reflect(pins);
	}

}
