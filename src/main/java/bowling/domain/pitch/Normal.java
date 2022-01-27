package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public class Normal implements Pitch{
    private KnockedPins knockedPins;

    private Normal() {
    }

    public Normal(KnockedPins knockedPins) {
        this.knockedPins = knockedPins;
    }

    public Pitch play(KnockedPins knockedPins) {
        System.out.println("Normal Pitch play enter");
        if (knockedPins.isStrike()) {
            return new Strike(knockedPins);
        }

        if (this.knockedPins.getKnockedPins() + knockedPins.getKnockedPins() == 10) {
            return new Spare(knockedPins);
        }

        return new Normal(knockedPins);
    }

    @Override
    public KnockedPins getKnockedPins() {
        return knockedPins;
    }
}
