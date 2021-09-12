package bowling.domain.state;

import java.util.Collections;
import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;

public class Spare extends BasePitchState {

	private final Pins pins;

	private Spare(final Pins pins) {
		this.pins = pins;
	}

	public static Spare of(final Pins pins) {
		return new Spare(pins);
	}

	@Override
	public List<Pins> getHitPins() {
		return Collections.singletonList(pins);
	}

	@Override
	public Score score() {
		return ProgressScore.ofSpare();
	}

	@Override
	public boolean isFinish() {
		return true;
	}

	@Override
	public boolean isAllHit() {
		return true;
	}

	@Override
	public Score addBonusScore(final Score score) {
		final Score resultScore = score.add(pins.score());

		if (resultScore.isComputeAble()) {
			return resultScore;
		}

		return resultScore.add(pins.spareScore());
	}
}
