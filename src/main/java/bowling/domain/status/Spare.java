package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

import static bowling.domain.status.Strike.STRIKE_SCORE;

public class Spare extends Finished {

    public Spare(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return new Score(STRIKE_SCORE, 1);
    }
}
