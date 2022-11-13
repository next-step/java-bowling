package bowling.domain.count;

import bowling.domain.Pitch;

public class Gutter extends Pitch {
    public Gutter(final int number) {
        super(number, 0);
    }
    
    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isGutter() {
        return true;
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
