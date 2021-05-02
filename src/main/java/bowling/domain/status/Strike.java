package bowling.domain.status;

import bowling.domain.Pitch;

public class Strike implements Status {
    private final Pitch current;

    public Strike(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);
        return new Final(pitch, 1);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonusPitch() {
        return true;
    }

    @Override
    public String display(int fallenPins) {
        return "X";
    }
}
