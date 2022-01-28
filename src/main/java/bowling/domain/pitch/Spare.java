package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public class Spare implements Pitch {
    private final KnockedPins knockedPins;

    public Spare(final KnockedPins knockedPins) {
        this.knockedPins = knockedPins;
    }

    @Override
    public Pitch play(final KnockedPins knockedPins) {
        if (knockedPins.isStrike()) {
            return new Strike(knockedPins);
        }
        return new Normal(knockedPins);
    }

    @Override
    public KnockedPins getKnockedPins() {
        return knockedPins;
    }
}
