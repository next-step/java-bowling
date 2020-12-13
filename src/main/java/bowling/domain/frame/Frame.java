package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.Pitchings;

public abstract class Frame {
    private final Pitchings pitchings;

    public Frame(Pitchings pitchings) {
        this.pitchings = pitchings;
    }

    abstract public Frame initNextFrame();

    abstract public Frame setKnockDownPins(KnockDownPins knockDownPins);

    abstract public int getIndex();

    public Pitchings getPitchings() {
        return pitchings;
    }

    public boolean isEnd() {
        return pitchings.isEnd();
    }
}
