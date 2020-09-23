package bowling.domain.score.normalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

import java.util.ArrayList;

public class SpareScore extends NormalFrameScore {

	private static final int SPARE_EXTRA_COUNT = 1;

	public SpareScore(DownedPinCount first, DownedPinCount second, Score accumulated) {
		super(SPARE_EXTRA_COUNT, accumulated, first, second);
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
