package bowling.state.domain;

import bowling.frame.domain.Score;
import bowling.pin.domain.Pin;

public class Miss extends Finished {

    private final Pin first;
    private final Pin second;

    private Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static State of(Pin first, Pin felled) {
        return new Miss(first, felled);
    }

    @Override
    public Score calculate(Score base) {
        base = base.add(first.toScore());
        if (base.isPending()) {
            base = base.add(second.toScore());
        }
        return base;
    }

    @Override
    public Score getScore() {
        return first.add(second).toScore();
    }

    @Override
    public String view() {
        return first + "|" + second;
    }
}
