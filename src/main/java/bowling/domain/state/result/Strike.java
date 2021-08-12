package bowling.domain.state.result;

import bowling.domain.score.ProgressScore;
import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

import static bowling.domain.pin.Pins.MAX_COUNT_HIT_PINS;

public class Strike extends End {

    public static Strike of() {
        return new Strike();
    }

    @Override
    public boolean isAllHit() {
        return true;
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.singletonList(MAX_COUNT_HIT_PINS);
    }

    @Override
    public Score score() {
        return ProgressScore.ofStrike();
    }

    @Override
    public Score addBonusScore(Score score) {
        return score.add(CommonScore.ofStrike());
    }

}
