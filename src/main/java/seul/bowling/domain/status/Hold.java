package seul.bowling.domain.status;

import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

public class Hold extends Running {
    private Pins pins;
    private Score score;

    public Hold(int clearPin) {
        this.pins = Pins.of(clearPin);
        this.score = Score.of(clearPin);
    }

    @Override
    public Status addPins(int clearPin) {
        this.pins.addPin(clearPin, false);
        this.score.addScore(clearPin);

        if (pins.allClear()) {
            return new Spare(pins, score);
        }
        return new Miss(pins, score);
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
    public boolean equalsStatus(Status status) {
        return this.getClass().equals(status.getClass());
    }
}
