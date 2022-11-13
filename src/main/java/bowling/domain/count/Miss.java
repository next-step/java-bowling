package bowling.domain.count;

import bowling.domain.Pitch;

public class Miss extends Pitch {
    public Miss(final int number, final int pinCount) {
        super(number, pinCount);
    }
    
    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isGutter() {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
