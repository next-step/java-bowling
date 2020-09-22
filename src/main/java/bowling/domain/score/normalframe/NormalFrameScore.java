package bowling.domain.score.normalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

import java.util.List;

public abstract class NormalFrameScore implements Score {

	private static final int EXHAUSTED_EXTRA_COUNT = 0;

	protected int remainExtraCount;
	protected Score accumulated;
	protected List<DownedPinCount> accumulatedCount;

	NormalFrameScore(int remainExtraCount, Score accumulated, DownedPinCount... counts) {
		this.remainExtraCount = remainExtraCount;
		this.accumulated = accumulated;
		this.accumulatedCount = List.of(counts);
	}

	NormalFrameScore(Score accumulated, DownedPinCount... counts) {
		this.remainExtraCount = 0;
		this.accumulated = accumulated;
		this.accumulatedCount = List.of(counts);
	}

	public boolean isRemainExtra() {
		return remainExtraCount > EXHAUSTED_EXTRA_COUNT;
	}

	public void addExtraCount(DownedPinCount count) {
		if(isRemainExtra()) {
			accumulatedCount.add(count);
			remainExtraCount--;
		}
	}

	public DownedPinCount getValue() {
		DownedPinCount sum = accumulatedCount.stream()
								.reduce(DownedPinCount.NO_PIN_DOWN, DownedPinCount::sum);
		return DownedPinCount.sum(sum, accumulated.getValue());
	}
}
