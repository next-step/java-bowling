package bowling.domain.frame;

public abstract class BaseFrame implements Frame {

    protected static int FIRST_TRIAL = 1;

    protected static int SECOND_TRIAL = 2;

    protected static int NONE_SCORE = -1;

    private int trial;

    private int totalScore;

    private final Frame prevFrame;

    protected BaseFrame(int trial, int totalScore, Frame prevFrame) {
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

    protected abstract void calculateWith(int baseScore);

    private boolean hasTotalScore() {
        return totalScore != NONE_SCORE;
    }

    private int baseScoreFromPrev() {
        int baseScore = 0;

        if (hasPrevFrame()) {
            baseScore = prevFrame.getTotalScore();
        }

        return baseScore;

    }

    private boolean hasPrevFrame() {
        return prevFrame != null;
    }

    private boolean noSet(int baseScore) {
        return baseScore == NONE_SCORE;
    }

    protected void increaseTrial() {
        this.trial++;
    }

    protected void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

}
