package bowling.domain.state;

import bowling.domain.Score;

public class Spare extends Result {

    public Spare(Pins pins) {
        super(pins);
        this.score = Score.getSpareScore();
    }

    @Override
    public Score addNextScore(Score score) {
        return addNextScore(score, pins.getSecondFall());
    }
}
