package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public class Strike implements Pitch{

    private final KnockedPins knockedPins;

    public Strike(KnockedPins knockedPins) {
        this.knockedPins = knockedPins;
    }


    @Override
    public Pitch play(KnockedPins knockedPins) {
        return new Strike(knockedPins);
    }
    @Override
    public KnockedPins getKnockedPins() {
        return knockedPins;
    }
}
