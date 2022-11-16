package bowling.domain.count;

import static bowling.domain.PitchFactory.*;

import bowling.domain.Pitch;

public class Strike extends Pitch {
    public Strike(final int number) {
        super(number, PIN_COUNT);
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
        return true;
    }
}
