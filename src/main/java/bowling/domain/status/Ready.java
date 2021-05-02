package bowling.domain.status;

import bowling.domain.Pitch;

public class Ready implements Status {
    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (pitch.isStrike()) {
            return new Strike();
        }

        return new Default(pitch);
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
        return null;
    }
}
