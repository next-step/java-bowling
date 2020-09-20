package bowling.domain.score.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

import static bowling.domain.DownedPinCount.NO_PIN_DOWN;

public class FinalFrameSecondScore implements Score {

	private final DownedPinCount first;
	private final DownedPinCount second;
	private Score accumulated;

	public FinalFrameSecondScore(DownedPinCount first, DownedPinCount second, Score accumulated) {
		this.first = first;
		this.second = second;
		this.accumulated = accumulated;
	}

	protected DownedPinCount sumThisFrameCount() {
		return DownedPinCount.sum(DownedPinCount.sum(first,second), accumulated.getValue());
	}

	@Override
	public boolean isRemainExtra() {
		return false;
	}

	@Override
	public void addExtraCount(DownedPinCount count) {

	}

	@Override
	public DownedPinCount getValue() {
		if(DownedPinCount.isSpare(first, second) || DownedPinCount.containsStrike(first, second)) {
			return NO_PIN_DOWN;
		}
		return sumThisFrameCount();
	}
}
