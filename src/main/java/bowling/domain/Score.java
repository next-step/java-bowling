package bowling.domain;

public class Score {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FRAME_MIN_SCORE = 0;
    private int score;

    public Score(int score) {
        checkScore(score);
        this.score = score;
    }

    private void checkScore(int score) {
        if (score < FRAME_MIN_SCORE || score > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(FRAME_MIN_SCORE + "~" + FRAME_MAX_SCORE + " 입력");
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    public boolean isSpare(Score score) {
        return this.score + score.getScore() == FRAME_MAX_SCORE;
    }

    public boolean isStrike() {
        return this.score == FRAME_MAX_SCORE;
    }
}
