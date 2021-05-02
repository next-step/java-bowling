package bowling.domain.status;

import bowling.domain.Pitch;

public class Spare extends Finished {
    private final Pitch current;

    public Spare(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        return new Final(pitch, 0);
    }

    @Override
    public boolean hasBonusPitch() {
        return true;
    }

    @Override
    public String display() {
        return "/";
    }
}
