package bowling.state;

import bowling.score.Score;

public interface State {

	boolean isEnd();

	State throwBowl(int throwCount);

	String symbol();

	boolean canScore();

	Score score();

	Score bonus(Score prevScore);
}
