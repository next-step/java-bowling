package bowling.domain;

public class FinalFrame extends Frame {

    private Score bonusScore;

    public FinalFrame(int frameNumber) {
        super(frameNumber);
    }

    @Override
    public boolean isAbleSecondTry() {
        return true;
    }

    public boolean isAbleBonusTry() {
        return isStrike() || isSpare();
    }

    public void bonusTry(int score) {
        bonusScore = new Score(score);
    }

    @Override
    public String toString() {
        String finalFrameResult = super.toString();
        if (bonusScore != null) {
            finalFrameResult += "|" + ScoreText.getScoreTextByScore(bonusScore.getScore());
        }
        return finalFrameResult;
    }
}
