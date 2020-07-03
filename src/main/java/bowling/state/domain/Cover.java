package bowling.state.domain;

import bowling.frame.domain.Remaining;
import bowling.frame.domain.Score;
import bowling.pin.domain.Pin;

public class Cover extends Running {

    private final Pin first;

    private Cover(Pin first) {
        this.first = first;
    }

    public static State of(Pin felled) {
        return new Cover(felled);
    }

    @Override
    public State roll(Pin felled) {
        Pin sum = first.add(felled);
        if (sum.isAllFelled()) {
            return Spare.of(first);
        }
        if (sum.isFelledAtAll()) {
            return Gutter.of();
        }
        return Miss.of(first, felled);
    }

    @Override
    public Score calculate(Score base) {
        return base.add(first.toScore());
    }

    @Override
    public Score getScore() {
        return first.toScore(Remaining.COVER);
    }

    @Override
    public String view() {
        return first.toString();
    }
}
