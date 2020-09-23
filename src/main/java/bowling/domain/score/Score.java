package bowling.domain.score;

import bowling.domain.DownedPinCount;

public interface Score {

	boolean isRemainExtra();

	void addExtraCount(DownedPinCount count);

	DownedPinCount getValue();
}
