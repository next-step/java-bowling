package bowling;

public class Trial {
    private static final int DOUBLE_STRIKE_NUMBER = 20;
    private static final int NON_MISS_NUMBER = 10;

    private final Scores scores = new Scores();
    private ScoreString scoreString = new ScoreString();

    public void add(Score score) {
        scores.add(score);
        scoreString = addScoreString(score);
    }

    public FrameResult getFrameResult() {
        if (scores.isTotalScoreHigherOrEqualThan(NON_MISS_NUMBER)) {
            return getSpareOrStrike();
        }

        return FrameResult.MISS;
    }

    public boolean isNormalEnd() {
        return scores.isTotalScoreHigherOrEqualThan(NON_MISS_NUMBER) || scores.isSecondScore();
    }

    public boolean isFinalEnd() {
        return isFullMiss() || scores.isAfterSecondScore();
    }

    public String getScoreString() {
        return scoreString.getOutputString();
    }

    private FrameResult getSpareOrStrike() {
        if (!scores.isSecondScore() || isDoubleStrike()) {
            return FrameResult.STRIKE;
        }

        return FrameResult.SPARE;
    }

    private boolean isDoubleStrike() {
        return scores.isSecondScore() && scores.isTotalScoreHigherOrEqualThan(DOUBLE_STRIKE_NUMBER);
    }

    private boolean isFullMiss() {
        return scores.isSecondScore() && getFrameResult().equals(FrameResult.MISS);
    }

    private ScoreString addScoreString(Score score) {
        if (scores.isTotalScoreHigherOrEqualThan(NON_MISS_NUMBER)) {
            return scoreString.append(score, getFrameResult());
        }

        return scoreString.append(score);
    }
}
