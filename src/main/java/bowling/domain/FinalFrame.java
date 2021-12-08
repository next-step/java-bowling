package bowling.domain;

import static bowling.domain.NormalFrame.INITIAL_REMAINDER;

public class FinalFrame implements Frame {
    private static final int MAX_PITCHES = 3;

    private final Pitches pitches;
    private int remainder;

    public FinalFrame() {
        this.pitches = new Pitches();
        remainder = INITIAL_REMAINDER;
    }

    public Pitches getPitches() {
        return pitches;
    }

    public Frame frameAfterPitch(int number) {
        pitches.add(new Pitch(number));

        if (continuable()) {
            return this;
        }

        return null;
    }

    public boolean continuable() {
        if (pitches.full(MAX_PITCHES)) {
            return false;
        }

        return pitches.isFirstPitch() || pitches.isSecondPitch() || pitches.containingStrikeOrSpare();
    }

    public Score score() {
        return pitches.sum();
    }

    @Override
    public void addPins(int pins) {

    }
}
