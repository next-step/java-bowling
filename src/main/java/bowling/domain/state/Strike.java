package bowling.domain.state;

import java.util.Collections;
import java.util.List;

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
	public List<Integer> getHitPins() {
		return Collections.singletonList(MAX_HIT_PINS_COUNT);
	}

	@Override
	public Score score() {
		return ProgressScore.ofStrike();
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
		return score.add(BaseScore.ofStrike());
	}
}
