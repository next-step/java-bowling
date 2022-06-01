package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Strike extends Ended{

    public static final int PITCH_COUNT = 1;
    public static final int STRIKE_VALUE = 10;
    private static final int STRIKE_REMAIN_PITCH_COUNT = 2;
    private static final int MAX_PIN_HIT_COUNT = 10;

    public Strike(Scores scores) {}

    @Override
    public Score calculateScore(Score before) {
        return before.addValue(STRIKE_VALUE);
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.strike();
    }

    @Override
    public Score score() {
        return new Score(MAX_PIN_HIT_COUNT, STRIKE_REMAIN_PITCH_COUNT);
    }

    public static boolean checkType(Scores scores){
        if(!scores.checkSize(PITCH_COUNT)){
            return false;
        }
        return scores.getFistScore() == STRIKE_VALUE;
    }

    @Override
    public String toString(){
        return "[Strike]";
    }
}
