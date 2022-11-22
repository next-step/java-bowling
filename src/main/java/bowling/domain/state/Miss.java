package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score2;

public class Miss extends Finished {

    private final PinCount fist;
    private final PinCount second;

    public Miss(PinCount fist, PinCount second) {
        this.fist = fist;
        this.second = second;
    }

    public static Miss from(int first, int second) {
        return new Miss(PinCount.of(first), PinCount.of(second));
    }

    @Override
    public Score2 getScore() {
        return new Score2(fist.sum(second), 0);
    }
}
