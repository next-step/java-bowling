package bowling.domian.frame;

import bowling.domian.state.State;

import java.util.Objects;

abstract class FrameResult {
    static final int TOTAL_SCORE_UNCALCULATED = -1;
    static final int TOTAL_SCORE_INITIAL = 0;

    State state;
    Score score;
    int totalScore;

    abstract boolean canCalculateScore();

    abstract boolean isCalculateDone();

    public abstract void calculateAdditional(FrameResult lastNormalFrameResult);

    public boolean isTotalCalcualted() {
        return this.totalScore != TOTAL_SCORE_UNCALCULATED;
    }

    public int addTotalScore(int totalScore) {
        this.totalScore = totalScore;

        return getScore();
    }

    public int getScore() {
        if (totalScore == TOTAL_SCORE_UNCALCULATED) {
            return score.getScore();
        }
        
        return score.getScore() + totalScore;
    }

}
