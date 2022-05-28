package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlTypeDto;
import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;

import java.util.List;

public class Gutter extends Ended{

    private static final int PITCH_COUNT = 2;
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

    public static boolean checkType(BowlTypeDto bowlTypeDto){
        List<Integer> scores = bowlTypeDto.getScores();
        if(scores.size() != PITCH_COUNT){
            return false;
        }
        int first = scores.get(0);
        int second = scores.get(1);
        int sum = first + second;
        return sum == GUTTER_VALUE;
    }
}
