package bowling.refactoring.domain.state;

import bowling.refactoring.domain.frame.*;

public class Spare extends Finished {

    private static final int SPARE_ADDITIONAL_SCORE = 1;
    private static final int SPARE_SCORE = 10;

    private int totalScore;
    private int additionalScore;

    public Spare() {
        this.totalScore = SPARE_SCORE;
        this.additionalScore = SPARE_ADDITIONAL_SCORE;
    }

    @Override
    public boolean isEndedCalculateScore() {
        return additionalScore == 0;
    }

    @Override
    public void calculateBonusScore(Frame nextFrame) {
        this.totalScore += nextFrame.score().firstScore().count();
        this.additionalScore--;
    }

    public int totalScore() {
        return this.totalScore;
    }
}
