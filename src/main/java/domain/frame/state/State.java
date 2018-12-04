package domain.frame.state;

import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public interface State {
	State tryBowl(Pin pin);

	boolean isFinished();

	Score getScore();

	Score calculateScore(Score previousScore);
}
