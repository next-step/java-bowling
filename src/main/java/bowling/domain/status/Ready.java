package bowling.domain.status;

import bowling.domain.Pitch;

public class Ready extends Continue {
    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (pitch.isStrike()) {
            return new Strike(pitch);
        }

        return new Hold(pitch);
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display() {
        throw new UnsupportedOperationException();
    }
}
