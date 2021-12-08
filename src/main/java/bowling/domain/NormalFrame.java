package bowling.domain;

public class NormalFrame implements Frame {
    public static final int INITIAL_REMAINDER = 2;

    private final Pitches pitches;
    private int remainder;

    public NormalFrame() {
        pitches = new Pitches();
        remainder = INITIAL_REMAINDER;
    }

    public Pitches getPitches() {
        return pitches;
    }

    public boolean continuable() {
        return pitches.continuableForNormalFrame();
    }

    public Frame pitch(int number) {
        pitches.add(new Pitch(number));

        if (pitches.continuableForNormalFrame()) {
            return this;
        }

        return new NormalFrame();
    }

    public Score score() {
        return pitches.sum();
    }

    public void addPins(int pins) {
        if (remainder == 0) {
            return;
        }

        remainder--;

        pitches.add(new Pitch(pins));
    }
}
