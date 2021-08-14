package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

import static bowling.domain.pin.Pins.MAX_COUNT_HIT_PINS;

public class Strike extends State {

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

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State hitPins(Pins pins) {
        throw new IllegalStateException("다음은 없습니다");
    }

}
