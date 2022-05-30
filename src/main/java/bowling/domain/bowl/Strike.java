package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Strike extends Ended{

    private static final int PITCH_COUNT = 1;
    private static final int STRIKE_VALUE = 10;

    @Override
    public String toString(){
        return "[Strike]";
    }

    @Override
    public Score calculateScore(Score before) {
        return before.addValue(STRIKE_VALUE);
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.strike();
    }

    public static boolean checkType(Scores scores){
        if(!scores.checkSize(PITCH_COUNT)){
            return false;
        }
        return scores.getFistScore() == STRIKE_VALUE;
    }
}
