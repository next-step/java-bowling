package bowling.domian.frame;

import bowling.domian.state.State;

abstract class FrameResult {
    static final int TOTAL_SCORE_UNCALCULATED = -1;

    State state;
    Score score;
    int totalScore;

    abstract boolean canCalculateScore();

    abstract boolean isCalculateDone();

    public abstract void calculateAdditional(FrameResult lastNormalFrameResult);

    public boolean isTotalCalculated() {
        return this.totalScore > TOTAL_SCORE_UNCALCULATED;
    }

    public int getTotalScore() {
        return score.getScore() + totalScore;
    }

    public void addLastTotalScore(FrameResult lastFrameResult) {
        this.totalScore = lastFrameResult.getTotalScore();
    }
}
