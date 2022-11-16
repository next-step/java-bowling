package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class FirstBowl extends Ongoing {

    public FirstBowl(Pin pin) {
        this.first = pin;
    }

    @Override
    public Status bowl(Pin second) {
        if (Pin.sumBowls(first, second).isAllPinsDown()) {
            return new Spare(first, second);
        }
        return new Miss(first, second);
    }

    @Override
    public Score getScore() {
        return new Score(this.getCountOfFirst(), 0);
    }

    @Override
    public Score addScore(Score score) {
        return new Score(score.getValue() + this.getCountOfFirst(), score.getNextScoreCnt() - 1);
    }
}
