package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public abstract class State {

    public abstract State hitPins(Pins pins);

    public abstract List<Integer> getHitPins();

    public List<State> getState() {
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

    public boolean isMiss() {
        return false;
    }

    public boolean isAllHit() {
        return false;
    }

    public boolean isStart() {
        return false;
    }

    protected Score addBonusScore(Score score) {
        return score;
    }

}
