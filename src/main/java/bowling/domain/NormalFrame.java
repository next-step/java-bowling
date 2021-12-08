package bowling.domain;

public class NormalFrame implements Frame {
    public static final int INITIAL_REMAINDER = 2;

    private static final int MAX_PITCHES = 2;

    private final Pitches pitches;
    private int remainder;

    public NormalFrame() {
        pitches = new Pitches();
        remainder = INITIAL_REMAINDER;
    }

    public Frame frameAfterPitch(int number) {
        decreaseRemainder();
        pitches.add(new Pitch(number));
        increaseRemainder();

        if (continuable()) {
            return this;
        }

        return new NormalFrame();
    }

    private void decreaseRemainder() {
        remainder--;
    }

    private void increaseRemainder() {
        if (pitches.containingFirstStrike()) {
            remainder += 1;
        }

        if (pitches.containingSpare()) {
            remainder += 1;
        }
    }

    public boolean continuable() {
        if (pitches.full(MAX_PITCHES)) {
            return false;
        }

        return pitches.isFirstPitch() || !pitches.containingFirstStrike();
    }

    public Score score() {
        return pitches.sum();
    }

    public boolean remainderLeft() {
        return remainder > 0;
    }

    public void addPins(int pins) {
        if (remainder == 0) {
            return;
        }

        decreaseRemainder();
        pitches.add(new Pitch(pins));
    }

    public String state() {
        return pitches.state();
    }
}
