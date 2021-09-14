package bowling.domain;

import java.util.List;

public abstract class Frame {

    protected final Pitches pitches;
    protected Frame nextFrame;

    public Frame(final Pitches pitches) {
        this.pitches = pitches;
        nextFrame = null;
    }

    public abstract boolean addPitchIfPossible(final Pitch pitch);

    public abstract boolean isFull();

    public abstract Score score();

    public void addNextFrame(final Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public List<Integer> pitchValues() {
        return pitches.values();
    }

}