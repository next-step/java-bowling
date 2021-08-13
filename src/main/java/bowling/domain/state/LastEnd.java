package bowling.domain.state;

import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class LastEnd extends End {

    private final BunchState bunchState;

    public LastEnd(BunchState bunchState) {
        this.bunchState = bunchState;
    }

    public static LastEnd of(BunchState bunchState) {
        return new LastEnd(bunchState);
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.emptyList();
    }

    @Override
    public List<CommonState> getState() {
        return bunchState.getState();
    }

    @Override
    public Score score() {
        return bunchState.score();
    }

    @Override
    public Score addBonusScore(Score score) {
        return bunchState.addScore(score);
    }


}
