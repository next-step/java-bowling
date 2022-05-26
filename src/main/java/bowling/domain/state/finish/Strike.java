package bowling.domain.state.finish;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.ImpossiblePitchException;

public class Strike implements Finish {

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

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public State pitch(Pins pins) {
        throw new ImpossiblePitchException(State.class.getName());
    }

}
