package bowling.domain.state.result;

import bowling.domain.score.Score;
import bowling.domain.state.BunchState;
import bowling.domain.state.CommonState;

import java.util.Collections;
import java.util.List;

public class Finish extends End {

    private final BunchState bunchState;

    public Finish(BunchState bunchState) {
        this.bunchState = bunchState;
    }

    public static Finish of(BunchState bunchState) {
        return new Finish(bunchState);
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
