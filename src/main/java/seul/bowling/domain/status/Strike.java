package seul.bowling.domain.status;

import lombok.Getter;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

@Getter
public class Strike extends Finished {
    private static final int BONUS_SCORE_COUNT = 2;

    private Pins pins;
    private Score score;

    public Strike(int clearPin) {
        this.pins = Pins.of(clearPin);
        this.score = Score.of(clearPin);
        this.score.addBonusScoreCount(BONUS_SCORE_COUNT);
    }

    @Override
    public Status addPins(int clearPin) {
        this.pins.addPin(clearPin, true);

        return this;
    }

    @Override
    public void addBonusScore(int bonusScore) {
        this.score.addBonusScore(bonusScore);
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
    public Pins getPins() {
        return this.pins;
    }

    @Override
    public boolean endCalculateScore() {
        return this.score.endAddBonusScore();
    }

    @Override
    public boolean equalsStatus(Status status) {
        return this.getClass().equals(status.getClass());
    }
}
