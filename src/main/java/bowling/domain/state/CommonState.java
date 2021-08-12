package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public interface CommonState {

    CommonState hitPins(Pins pins);

    List<Integer> getHitPins();

    default List<CommonState> getState() {
        return Collections.singletonList(this);
    }

    default Score score() {
        return CommonScore.ofBase();
    }

    default Score addScore(Score score) {
        if (score.isCompute()) {
            return score;
        }

        return addBonusScore(score);
    }

    default Score addBonusScore(Score score) {
        return score;
    }

    default boolean isFinish() {
        return false;
    }

    default boolean isMiss() {
        return false;
    }

    default boolean isAllHit() {
        return false;
    }

}
