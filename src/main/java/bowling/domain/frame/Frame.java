package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.Pitchings;

public abstract class Frame {
    private final Pitchings pitchings;

    public Frame(Pitchings pitchings) {
        this.pitchings = pitchings;
    }

    abstract public Frame initNextFrame();

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        pitchings.addPitching(knockDownPins);
    }

    public Pitchings getPitchings() {
        return pitchings;
    }

    public boolean isEnd() {
        return pitchings.isEnd();
    }
}
