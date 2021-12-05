package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public interface State {
	State bowl(Pins pins);

	boolean isEnd();

	String symbol();

	Score score();

	Score calculateAdditionalScore(Score prevScore);
}
