package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public class Normal implements Pitch {
    private KnockedPins knockedPins;

    public Normal() {
    }

    public Normal(KnockedPins knockedPins) {
        this.knockedPins = knockedPins;
    }

    public Normal(int count) {
        this(new KnockedPins(count));
    }

    public Pitch play(KnockedPins knockedPins) {
        if (knockedPins.isStrike()) {
            return new Strike(knockedPins);
        }

        if (this.knockedPins.getCount() + knockedPins.getCount() == 10) {
            return new Spare(knockedPins);
        }

        return new Normal(knockedPins);
    }

    @Override
    public KnockedPins getKnockedPins() {
        return knockedPins;
    }
}
