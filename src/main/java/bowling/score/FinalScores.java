package bowling.score;

import java.util.Arrays;
import java.util.List;

public class FinalScores extends Scores {
    private Score bonusScore;

    private FinalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
        this.bonusScore = null;
    }

    public static Scores init() {
        return new FinalScores(null, null);
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
    public boolean canBowl() {
        if (!hasFirstScore()) {
            return true;
        }
        if (!hasSecondScore() && !hasBonusScore()) {
            return true;
        }
        if (FinalScores.isSpare(this) && !hasBonusScore()) {
            return true;
        }
        return false;
    }

    private static boolean isSpare(Scores scores) {
        Score firstScore = scores.firstScore;
        Score secondScore = scores.secondScore;

        if (firstScore != null && secondScore != null) {
            return firstScore.sum(secondScore).isStrike();
        }

        return false;
    }

    private boolean hasBonusScore() {
        return bonusScore != null;
    }

    @Override
    public List<Score> getResult() {
        return Arrays.asList(firstScore, secondScore, bonusScore);
    }
}
