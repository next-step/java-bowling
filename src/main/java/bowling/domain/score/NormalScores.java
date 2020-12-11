package bowling.domain.score;

import java.util.Arrays;
import java.util.List;

public class NormalScores extends Scores {

    private NormalScores(Score firstScore, Score secondScore) {
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
    public boolean canBowl() {
        if (!hasFirstScore()) {
            return true;
        }

        if (isStrike()) {
            return false;
        }

        return !isDone();
    }

    private boolean isDone() {
        return hasFirstScore() && hasSecondScore();
    }

    private boolean isStrike() {
        return firstScore != null && firstScore.isStrike();
    }

    @Override
    public List<Score> getResult() {
        return Arrays.asList(firstScore, secondScore);
    }
}