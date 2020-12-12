package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;

public abstract class Frame {
    public static final int FIRST_FRAME = 1;
    public static final int LAST_FRAME = 10;
    public static final int SPARE_BONUS_COUNT = 1;

    protected int frameNumber;
    protected Scores scores;
    Frame next;

    Frame(int frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    public int getNumber() {
        return frameNumber;
    }

    public boolean getBy(int frameNumber) {
        return this.frameNumber == frameNumber;
    }

    public List<Score> getResult() {
        return scores.getResult();
    }

    public void bowl(Score score) {
        scores.add(score);
    }

    public boolean canBowl() {
        return scores.canBowl();
    }

    public abstract Frame next();

    public Score getTotalScore() {
        if (scores.isSpare()) {
            return getSpareTotalScore();
        }

        if (scores.isStrike()) {
            return getStrikeTotalScore();
        }

        return canBowl() ? Score.INVALID_SCORE : scores.getTotalScore();
    }

    protected abstract Score getStrikeTotalScore();

    protected abstract Score getSpareTotalScore();

    protected abstract Score getTwoStrikeTotalScore(Score lastTotalScore);

    protected Score getScore(int bonusScoreCount) {
        return scores.getScore(bonusScoreCount);
    }

    boolean hasFirstScore() {
        return scores.hasFirstScore();
    }
}
