package bowling.domain;

public class FinalFrame implements Frame {

    private final Pitches pitches = new Pitches();

    @Override
    public void bowl(int hitCounts) {
        pitches.recordPitch(hitCounts);
    }

    @Override
    public Frame next() {
        return pitches.isMovableToNextPitch() ? this : null;
    }

    @Override
    public Pitches getPitches() {
        return pitches;
    }
}
