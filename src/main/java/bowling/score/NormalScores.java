package bowling.score;

import java.util.Arrays;
import java.util.List;

public class NormalScores extends Scores {

    public NormalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static Scores init() {
        return new NormalScores(null, null);
    }

    @Override
    public void add(Score score) {
        if (firstScore == null) {
            firstScore = score;
            return;
        }

        secondScore = score;
    }

    @Override
    public boolean hasBonusScore() {
        return false;
    }

    @Override
    public List<Score> getResult() {
        return Arrays.asList(firstScore, secondScore);
    }
}