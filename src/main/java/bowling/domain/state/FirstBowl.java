package bowling.domain.state;

import bowling.domain.PinCount;

public class FirstBowl extends Running {

    private final PinCount first;

    public FirstBowl(PinCount first) {
        this.first = first;
    }

    @Override
    public State next(PinCount second) {
        if (PinCount.MAX > first.sum(second)) {
            return new Miss(first, second);
        }

        if (PinCount.MAX == first.sum(second)) {
            return new Spare(first, second);
        }

        throw new IllegalArgumentException("유효하지 않은값 입니다. " + second);
    }
}
