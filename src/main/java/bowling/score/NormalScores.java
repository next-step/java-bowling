package bowling.score;

import java.util.ArrayList;
import java.util.List;

public class NormalScores extends Scores {

    public NormalScores(List<Score> scores) {
        super(scores);
    }

    public static Scores newInstance() {
        return new NormalScores(new ArrayList<>());
    }

    @Override
    public boolean canPitching() {
        if (isInitCount()) {
            return true;
        }
        if (isSpare()) {
            return false;
        }
        if (getScore().isStrike()) {
            return false;
        }
        if (isSecondCount()) {
            return false;
        }
        return (isFirstCount() || getScore().isGutter());
    }

}
