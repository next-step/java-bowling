package bowling.domain.frame;

import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.PitchStates;

public interface Frame {

	Score addScore(Score score);

	void addFrame(final List<Frame> defaultFrames);

	void hitPins(Pins pins);

	boolean isFinish();

	boolean isStart();

	PitchStates getPitchStates();

	Score getScore();
}
