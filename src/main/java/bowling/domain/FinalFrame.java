package bowling.domain;

public class FinalFrame implements Frame {
    private static final int MAX_PITCHES = 3;

    private final Pitches pitches;

    public FinalFrame() {
        this.pitches = new Pitches();
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
    public boolean remainderLeft() {
        return false;
    }

    @Override
    public void addPins(int pins) {
    }

    public String state() {
        return pitches.finalState();
    }
}
