package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Score;

public interface State {

	State roll(int pinCount);
	boolean isFinished();
	Score getScore();
	Score calculateBonus(Score score);
	String getResult();
}
