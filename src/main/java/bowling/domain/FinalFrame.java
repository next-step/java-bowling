package bowling.domain;

public class FinalFrame implements Frame {

    private final int index;
    private final Pitches pitches = new Pitches();

    private FinalFrame(int index) {
        this.index = index;
    }

    public static FinalFrame last(int index) {
        return new FinalFrame(index);
    }

    @Override
    public Frame next() {
        return pitches.isMovableToNextPitch() ? this : null;
    }

    @Override
    public void bowl(int hitCounts) {
        pitches.recordPitch(hitCounts);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Pitches getPitches() {
        return pitches;
    }
}
