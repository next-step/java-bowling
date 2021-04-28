package step4.domain.state;

import step4.domain.PinCount;
import step4.domain.Score;
import step4.domain.Symbol;

public class Strike extends Finished {

    @Override
    public Score score() {
        return Score.Strike();
    }

    @Override
    public Score addScore(Score score) {
        if (!score.isOpportunityLeft()) {
            return score;
        }

        return score.add(PinCount.BOUND_MAX);
    }

    @Override
    public String marks() {
        return Symbol.of(this, new PinCount(PinCount.BOUND_MAX), true);
    }
}
