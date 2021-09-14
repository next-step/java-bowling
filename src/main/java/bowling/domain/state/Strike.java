package bowling.domain.state;

import java.util.Collections;
import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.BaseScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;

public class Strike extends BasePitchState {

	private Strike() {
	}

	public static Strike of() {
		return new Strike();
	}

	@Override
	public List<Pins> getHitPins() {
		return Collections.singletonList(Pins.of(MAX_HIT_PINS_COUNT));
	}

	@Override
	public Score score() {
		return ProgressScore.ofStrike();
	}

	@Override
	public Score addBonusScore(final Score score) {
		return score.add(BaseScore.ofStrike());
	}
}
