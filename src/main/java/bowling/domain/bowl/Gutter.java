package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;

public class Gutter extends Ended{

    private static final int GUTTER_VALUE = 0;

    @Override
    public String toString(){
        return "[Gutter]";
    }

    @Override
    public Score score() {
        return Score.gutter();
    }

    @Override
    public Score calculateScore(Score before) {
        Score after = before.addValue(GUTTER_VALUE);
        if(after.isFinished()){
            return after;
        }
        return after.addValue(GUTTER_VALUE);
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.gutter();
    }
}
