package bowling.domain;

import static bowling.domain.NormalFrame.INITIAL_REMAINDER;

public class FinalFrame implements Frame {
    private final Pitches pitches;
    private int remainder;

    public FinalFrame() {
        this.pitches = new Pitches();
        remainder = INITIAL_REMAINDER;
    }

    public Pitches getPitches() {
        return pitches;
    }

    public Frame pitch(int number) {
        pitches.add(new Pitch(number));

        if (pitches.continuableForFinalFrame()) {
            return this;
        }

        return null;
    }

    public boolean continuable() {
        return pitches.continuableForFinalFrame();
    }

    public Score score() {
        return pitches.sum();
    }

    @Override
    public void addPins(int pins) {

    }
}
