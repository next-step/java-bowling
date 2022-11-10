package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Miss extends Finished {

    public Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return new Score(this.getCountOfFirst() + this.getCountOfSecond(), 0);
    }
}
