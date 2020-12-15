package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.pitchings.Pitchings;

public abstract class Frame {
    private final Pitchings pitchings;
    protected Integer totalScore;

    public Frame(Pitchings pitchings) {
        this.pitchings = pitchings;
        totalScore = null;
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

    public abstract Integer getScore();

    public abstract Integer getTotalScore();

    public abstract Pitching getNextPitching();

    public abstract Pitching getNextAndNextPitching();
}
