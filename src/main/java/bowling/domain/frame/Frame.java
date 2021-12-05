package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;

public interface Frame {
	Frame bowl(Pins pins);

	boolean isEnd();

	int getFrameIndex();

	String symbol();

	int score();

	int calculateAdditionalScore(Score prevScore);
}
