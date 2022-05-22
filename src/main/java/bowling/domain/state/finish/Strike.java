package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public class Strike extends Finish {

    private static final String STRIKE = "X";

    private Strike() {
    }

    public static State create() {
        return new Strike();
    }

    @Override
    public String getSymbol() {
        return STRIKE;
    }

    @Override
    public Score score() {
        return Score.strike();
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        if (beforeScore.finishCalculation()) {
            return beforeScore;
        }
        return beforeScore.addBonusScore(score().score());
    }

}
