package bowling.domain.frame;

import bowling.domain.score.Score;

public interface Frame {
	Frame addNextFrame();

	boolean canPlayMore();

	int getIndex();

	void addFirstScore(Score firstScore);

	void addSecondScore(Score secondScore);

	void addBonusScore(Score bonusScore);
}
