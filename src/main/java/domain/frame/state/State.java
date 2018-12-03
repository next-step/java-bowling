package domain.frame.state;

import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public interface State {
	State tryBowl(Pin pin);

	int getFrameNumber();

	boolean isFinished();

	default boolean isSameFrameNumber(int frameNumber) {
		return getFrameNumber() == frameNumber;
	}

	Score getScore();

	Score calculateScore(Score previousScore);
}
