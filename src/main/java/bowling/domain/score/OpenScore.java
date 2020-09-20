package bowling.domain.score;

import bowling.domain.DownedPinCount;

import java.util.ArrayList;
import java.util.List;

public class OpenScore implements Score {

	private int remainExtraCount = 0;
	private List<DownedPinCount> accumulatedCount;
	private Score accumulated;

	public OpenScore(DownedPinCount first, DownedPinCount second, Score accumulated) {
		List<DownedPinCount> list = new ArrayList<>();
		list.add(first);
		list.add(second);
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
