package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

public class FinalScoreCalculator implements ScoreCalculator {
    private static final int MAX_ADD_NUMBER_WITH_THIRD = 3;
    private static final int MAX_ADD_NUMBER = 2;
    private static final int INIT_VALUE = 0;
    private static final int ZERO = 0;

    private int maxAddNumber = ZERO;
    private int score;
    private boolean hasThirdChance = false;

    public FinalScoreCalculator() {
        this.score = INIT_VALUE;
    }

    @Override public Score calculateScore(Frame frame) {
        return calculateScore();
    }

    @Override public Score calculateScore() {
        if (isAddingDone()) {
            return new CompleteScore(score);
        }

        return EmptyScore.valueOf();
    }

    @Override public ScoreCalculator add(Pitch pitch) {
        if (isAddingDone()) {
            return this;
        }

        if (hasThirdChance(pitch)) {
            hasThirdChance = true;
        }

        maxAddNumber++;
        score += pitch.getPinCount();
        return this;
    }

    private boolean hasThirdChance(Pitch pitch) {
        return pitch.isSpare() || pitch.isStrike();
    }

    private boolean isAddingDone() {
        return isDoneWithNoThirdChance() || isDoneWithThirdChance();
    }

    private boolean isDoneWithNoThirdChance() {
        return maxAddNumber == MAX_ADD_NUMBER && !hasThirdChance;
    }

    private boolean isDoneWithThirdChance() {
        return maxAddNumber == MAX_ADD_NUMBER_WITH_THIRD && hasThirdChance;
    }
}
