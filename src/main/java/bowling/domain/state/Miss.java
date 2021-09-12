package bowling.domain.state;

import java.util.Arrays;
import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.Score;
import bowling.domain.state.exception.InvalidCreateStateException;

public class Miss extends BasePitchState {

	private final Pins first;
	private final Pins second;

	private Miss(final Pins first, final Pins second) {
		createValidation(first, second);

		this.first = first;
		this.second = second;
	}

	private void createValidation(final Pins first, final Pins second) {
		if (first.add(second).isBiggerThan(MAX_HIT_PINS_COUNT - 1)) {
			throw new InvalidCreateStateException();
		}
	}

	public static Miss of(final Pins firstPins, final Pins secondPins) {
		return new Miss(firstPins, secondPins);
	}

	@Override
	public List<Pins> getHitPins() {
		return Arrays.asList(first, second);
	}

	@Override
	public Score score() {
		return ComputableScore.of(first.score().add(second.score()));
	}

	@Override
	public boolean isFinish() {
		return true;
	}

	@Override
	public boolean isMiss() {
		return true;
	}

	@Override
	public Score addBonusScore(final Score score) {
		final Score resultScore = score.add(first.score());

		if (resultScore.isComputeAble()) {
			return resultScore;
		}

		return resultScore.add(second.score());
	}
}
