package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public abstract class CommonState {

    public abstract CommonState hitPins(Pins pins);

    public abstract List<Integer> getHitPins();

    public List<CommonState> getState() {
        return Collections.singletonList(this);
    }

    public Score score() {
        return CommonScore.ofBase();
    }

    public Score addScore(Score score) {
        if (score.isCompute()) {
            return score;
        }

        return addBonusScore(score);
    }

    public boolean isFinish() {
        return false;
    }

    protected boolean isMiss() {
        return false;
    }

    protected Score addBonusScore(Score score) {
        return score;
    }

    protected boolean isAllHit() {
        return false;
    }

}
