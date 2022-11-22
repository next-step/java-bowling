package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score2;

public class Spare extends Finished {

    private final PinCount first;
    private final PinCount second;

    public Spare(PinCount first, PinCount second) {
        if (PinCount.MAX != first.sum(second)) {
            throw new IllegalArgumentException("합은 10이어야 합니다");
        }

        this.first = first;
        this.second = second;
    }

    @Override
    public Score2 getScore() {
        return new Score2(PinCount.MAX, 1);
    }
}
