package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlTypeDto;
import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import java.util.List;

public class Strike extends Ended{

    private static final int PITCH_COUNT = 1;
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

    public static boolean checkType(BowlTypeDto bowlTypeDto){
        List<Integer> scores = bowlTypeDto.getScores();
        if(scores.size() != PITCH_COUNT){
            return false;
        }
        int first = scores.get(0);
        return first == STRIKE_VALUE;
    }
}
