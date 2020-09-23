package bowling.domain.score.normalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

import java.util.ArrayList;

public class StrikeScore extends NormalFrameScore {

	private static final int STRIKE_EXTRA_COUNT = 2;

	public StrikeScore(Score accumulated) {
		super(STRIKE_EXTRA_COUNT, accumulated, DownedPinCount.ALL_PIN_DOWN);
		accumulatedCount = new ArrayList<>(accumulatedCount);
	}

	@Override
	public DownedPinCount getValue() {
		if(isRemainExtra()) {
			return DownedPinCount.NO_PIN_DOWN;
		}
		return super.getValue();
	}
}
