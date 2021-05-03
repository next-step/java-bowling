package bowling.domain.status;

import bowling.domain.Pitch;

public class Miss extends Finished {
    private static final int NO_BONUS_PITCH = 0;

    private final Pitch current;

    public Miss(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public int bonusPitchCount() {
        return NO_BONUS_PITCH;
    }

    @Override
    public String display() {
        return String.valueOf(current.intValue());
    }
}
