package bowling.domain.status;

import bowling.domain.Pitch;

public class Spare extends Finished {
    private static final int BONUS_PITCH_COUNT = 1;

    private final Pitch current;

    public Spare(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (pitch.isStrike()) {
            return new Bonus(pitch, BONUS_PITCH_COUNT, new Strike());
        }

        return new Bonus(pitch, BONUS_PITCH_COUNT, new Hold(pitch));
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
