package bowling.domain.frame;

import java.util.List;

public abstract class Frame {

    protected static int FIRST_TRIAL = 1;

    protected static int SECOND_TRIAL = 2;

    protected static int NONE_SCORE = -1;

    private int trial;

    private int totalScore;

    private final Frame prevFrame;

    protected Frame(int trial, int totalScore, Frame prevFrame) {
        this.trial = trial;
        this.totalScore = totalScore;
        this.prevFrame = prevFrame;
    }

    public boolean isNowFirstTry() {
        return trial == FIRST_TRIAL;
    }

    public boolean isNowSecondTry() {
        return trial == SECOND_TRIAL;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int calculateScore() {

        if (hasTotalScore()) {
            return totalScore;
        }

        int baseScore = baseScoreFromPrev();

        if (noSet(baseScore)) {
            return NONE_SCORE;
        }

        calculateWith(baseScore);

        return totalScore;
    }

    protected boolean hasTotalScore() {
        return totalScore != NONE_SCORE;
    }

    protected int baseScoreFromPrev() {
        int baseScore = 0;

        if (hasPrevFrame()) {
            baseScore = prevFrame.getTotalScore();
        }

        return baseScore;

    }

    protected boolean hasPrevFrame() {
        return prevFrame != null;
    }

    protected boolean noSet(int baseScore) {
        return baseScore == NONE_SCORE;
    }

    protected void increaseTrial() {
        this.trial++;
    }

    protected void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public abstract int next();

    public abstract List<Integer> getAllScores();

    public abstract Frame tryNext(int score);

    protected abstract void calculateWith(int baseScore);

    public abstract boolean isLast();

    protected abstract int addWithFirstScore(int score);

    protected abstract Frame getNextFrame();
}
