package bowling.domain.score;

import bowling.domain.Pin;

import static bowling.utils.BowlingConstants.*;

public class Score {

    private int score;
    private int bonusCount;

    private Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score initialize() {
        return new Score(INITIAL_SCORE, 0);
    }

    public static Score of(Pin pin, int bonusCount) {
        return new Score(pin.getPinNum(), bonusCount);
    }

    public Score calculateScore(int newScore) {
        return new Score(this.score + newScore, this.bonusCount - ONE);
    }

    public boolean hasBonusScore() {
        return this.bonusCount > ZERO;
    }

    public static int scoreForFrame(Score frameScore) {
        return frameScore.score;
    }

    public int getBonusCount() {
        return bonusCount;
    }

}

