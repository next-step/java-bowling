package bowling.domain.status;

import bowling.domain.Pitch;

public class Miss extends Finished {
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
    public String display() {
        return String.valueOf(current.intValue());
    }
}
