package bowling.domain.state;

import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;

public interface PitchState {

	PitchState hitPins(Pins pins);

	List<Pins> getHitPins();

	Score score();

	Score addScore(final Score score);

	boolean isProgress();

	boolean isFinish();

	boolean isAllHit();

	boolean isMiss();

	Score addBonusScore(final Score score);
}
