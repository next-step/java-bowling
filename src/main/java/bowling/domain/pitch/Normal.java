package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public class Normal implements Pitch{
    private KnockedPins knockedPins;
    public Normal(KnockedPins knockedPins) {
        this.knockedPins = knockedPins;
    }

    public boolean isSpare(KnockedPins knockedPins) {
        return this.knockedPins.getKnockedPins() + knockedPins.getKnockedPins() == 10;
    }
}
