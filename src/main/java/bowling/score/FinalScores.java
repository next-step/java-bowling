package bowling.score;

import java.util.ArrayList;
import java.util.List;

public class FinalScores extends Scores{

    protected FinalScores(List<Score> scores) {
        super(scores);
    }

    public static Scores newInstance() {
        return new FinalScores(new ArrayList<>());
    }

    @Override
    public boolean canPitching() {
        if (isFirstCount() && (getScore().isStrike() && !bonus)) {
            bonus = true;
            return true;
        }
        if (isFirstCount()) {
            return true;
        }
        //scores.size() != NUMBER_ZERO
        if (isSecondCount() && getScore().isStrike()) {
            bonus = true;
            return true;
        }
        if (isSecondCount() && bonus) {
            return true;
        }
        if (isSpare() && !bonus) {
            bonus = true;
            return true;
        }
        return false;
    }
}
