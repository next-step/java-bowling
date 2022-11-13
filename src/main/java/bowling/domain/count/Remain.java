package bowling.domain.count;

import bowling.domain.Pitch;

public class Remain extends Pitch {
    public Remain(final int number, final int count) {
        super(number, count);
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
