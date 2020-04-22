package seul.bowling.domain.status;

import lombok.Getter;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

@Getter
public class Miss extends Finished {
    private Pins pins;
    private Score score;

    public Miss(Pins pins, Score score) {
        this.pins = pins;
        this.score = score;
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
