package bowling.domain.score.normalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

public class OpenScore extends NormalFrameScore {

	public OpenScore(DownedPinCount first, DownedPinCount second, Score accumulated) {
		super(accumulated, first, second);
	}

	@Override
	public void addExtraCount(DownedPinCount count) {}
}
