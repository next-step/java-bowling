package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public class Spare implements Pitch {
    private KnockedPins knockedPins;
    public Spare(KnockedPins knockedPins) {
        this.knockedPins = knockedPins;
    }

    @Override
    public boolean isSpare(KnockedPins knockedPins) {
        return false;
    }
}
