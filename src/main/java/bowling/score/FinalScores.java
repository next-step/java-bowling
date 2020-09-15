package bowling.score;

import java.util.Arrays;
import java.util.List;

public class FinalScores extends Scores {
    private Score bonusScore;

    public FinalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
        this.bonusScore = null;
    }

    public static Scores init() {
        return new FinalScores(null, null);
    }

    public static boolean isSpare(Scores scores) {
        Score firstScore = scores.firstScore;
        Score secondScore = scores.secondScore;

        if (firstScore != null && secondScore != null) {
            return firstScore.sum(secondScore).isStrike();
        }

        return false;
    }

    @Override
    public void add(Score score) {
        if (firstScore == null) {
            firstScore = score;
            return;
        }

        if (secondScore == null && !firstScore.isStrike()) {
            secondScore = score;
            return;
        }

        bonusScore = score;
    }

    @Override
    public boolean hasBonusScore() {
        return bonusScore != null;
    }

    @Override
    public List<Score> getResult() {
        return Arrays.asList(firstScore, secondScore, bonusScore);
    }
}
