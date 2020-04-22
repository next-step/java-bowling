package seul.bowling.domain.status;

import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

public class Spare extends Finished {
    private static final int BONUS_SCORE_COUNT = 1;

    private Pins pins;
    private Score score;

    public Spare(Pins pins, Score score) {
        this.pins = pins;
        this.score = score;
        this.score.addBonusScoreCount(BONUS_SCORE_COUNT);
    }

    @Override
    public void addBonusScore(int bonusScore) {
        this.score.addBonusScore(bonusScore);
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public void addCumulativeScore(int score) {
        this.score.addCumulativeScore(score);
    }

    @Override
    public int getToTalScore() {
        return this.score.getScore();
    }

    @Override
    public boolean endCalculateScore() {
        return this.score.endAddBonusScore();
    }

    @Override
    public Pins getPins() {
        return this.pins;
    }

    @Override
    public boolean equalsStatus(Status status) {
        return this.getClass().equals(status.getClass());
    }
}
