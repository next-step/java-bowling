package bowling.domain.score.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;

public class FinalFrameBonusScore extends FinalFrameSecondScore {

	private final DownedPinCount bonus;

	public FinalFrameBonusScore(DownedPinCount first, DownedPinCount second, DownedPinCount bonus, Score accumulated) {
		super(first, second, accumulated);
		this.bonus = bonus;
	}

	@Override
	public DownedPinCount getValue() {
		return DownedPinCount.sum(sumThisFrameCount(), bonus);
	}
}
