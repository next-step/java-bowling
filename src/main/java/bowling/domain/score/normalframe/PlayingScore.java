package bowling.domain.score.normalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

public class PlayingScore extends NormalFrameScore {

	public PlayingScore(DownedPinCount first, Score accumulated) {
		super(accumulated, first);
	}

	@Override
	public void addExtraCount(DownedPinCount count) {}

	@Override
	public DownedPinCount getValue() {
		return DownedPinCount.NO_PIN_DOWN;
	}
}
