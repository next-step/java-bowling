package bowling.domain.status;

import bowling.domain.Pitch;

public class Default implements Status {

    private Pitch current;

    public Default(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (current.isSpare(fallenPins)) {
            return new Spare(pitch);
        }

        return new Open(pitch);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }
}
