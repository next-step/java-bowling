package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;

public class Strike extends Ended{

    private static final int STRIKE_VALUE = 10;

    @Override
    public String toString(){
        return "[Strike]";
    }

    @Override
    public Score score() {
        return Score.strike();
    }

    @Override
    public Score calculateScore(Score before) {
        return before.addValue(STRIKE_VALUE);
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.strike();
    }

}
