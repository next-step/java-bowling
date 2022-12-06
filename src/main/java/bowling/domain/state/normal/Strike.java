package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Finished;

public class Strike extends Finished {

    @Override
    public Score getScore() {
        return new Score(PinCount.MAX, 2);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        return beforeScore.add(PinCount.of(PinCount.MAX));
    }

    @Override
    public String getDesc() {
        return "X";
    }
}
