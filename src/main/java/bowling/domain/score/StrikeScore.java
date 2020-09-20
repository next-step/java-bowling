package bowling.domain.score;

import bowling.domain.DownedPinCount;

import java.util.ArrayList;
import java.util.List;

public class StrikeScore implements Score {

	private int remainExtraCount = 2;
	private List<DownedPinCount> accumulatedCount;
	private Score accumulated;

	public StrikeScore(Score accumulated) {
		List<DownedPinCount> list = new ArrayList<>();
		list.add(DownedPinCount.ALL_PIN_DOWN);
		this.accumulatedCount = list;
		this.accumulated = accumulated;
	}

	@Override
	public boolean isRemainExtra() {
		return remainExtraCount > 0;
	}

	@Override
	public void addExtraCount(DownedPinCount count) {
		if(isRemainExtra()) {
			accumulatedCount.add(count);
			remainExtraCount--;
		}
	}

	@Override
	public DownedPinCount getValue() {
		if(isRemainExtra()) {
			return DownedPinCount.NO_PIN_DOWN;
		}
		DownedPinCount sum = accumulatedCount.stream().reduce(DownedPinCount.NO_PIN_DOWN, DownedPinCount::sum);
		return DownedPinCount.sum(sum, accumulated.getValue());
	}
}
