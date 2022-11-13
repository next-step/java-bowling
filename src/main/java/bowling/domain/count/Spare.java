package bowling.domain.count;

import bowling.domain.Pitch;

public class Spare extends Pitch {
    public Spare(final int number, final int count) {
        super(number, count);
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public boolean isGutter() {
        return false;
    }
}
