package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Gutter extends Ended{

    public static final int PITCH_COUNT = 2;
    public static final int GUTTER_VALUE = 0;
    private static final int GUTTER_SCORE_VALUE = 0;
    private static final int DEFAULT_BONUS_PITCH_COUNT = 0;

    public Gutter(Scores scores) {}

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

    @Override
    public Score score() {
        return new Score(GUTTER_SCORE_VALUE, DEFAULT_BONUS_PITCH_COUNT);
    }

    public static boolean checkType(Scores scores){
        if(!scores.checkSize(PITCH_COUNT)){
            return false;
        }
        return scores.getScoreSum() == GUTTER_VALUE;
    }

    @Override
    public String toString(){
        return "[Gutter]";
    }

}
