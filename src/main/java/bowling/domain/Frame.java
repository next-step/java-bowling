package bowling.domain;

public class Frame {
    public static final int FIRST_FRAME_NUMBER = 1;
    public static final int FINAL_FRAME_NUMBER = 10;
    private int frameNumber;
    private Score score1;
    private Score score2;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public void firstTry(int score) {
        score1 = new Score(score);
    }

    public void secondTry(int score) {
        score2 = new Score(score);
    }

    public boolean isAbleSecondTry() {
        return score1 != null && score1.getScore() < ScoreText.STRIKE.getScore();
    }

    public boolean isStrike() {
        return score1.getScore() == ScoreText.STRIKE.getScore();
    }

    public boolean isSpare() {
        return !isStrike() && (score1.getScore() + score2.getScore() == ScoreText.STRIKE.getScore());
    }

    public boolean isFinalFrame() {
        return frameNumber == FINAL_FRAME_NUMBER;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public Score getScore1() {
        return score1;
    }

    public Score getScore2() {
        return score2;
    }

    @Override
    public String toString() {
        String frameScoreResult = ScoreText.getScoreTextByScore(score1.getScore());
        if (score2 != null) {
            frameScoreResult += "|" + (isSpare() ? "/" : ScoreText.getScoreTextByScore(score2.getScore()));
        }
        return frameScoreResult;
    }
}
