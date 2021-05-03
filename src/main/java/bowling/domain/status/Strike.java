package bowling.domain.status;

import bowling.domain.Pitch;

public class Strike extends Finished {
    private static final int BONUS_PITCH_COUNT = 2;

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
    public int bonusPitchCount() {
        return BONUS_PITCH_COUNT;
    }

    @Override
    public String display() {
        return "X";
    }
}
