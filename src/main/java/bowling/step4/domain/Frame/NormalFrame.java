package bowling.step4.domain.Frame;

import bowling.step4.domain.Pitches;


public class NormalFrame implements Frame {
    private final Pitches pitches;
    private static final int DEFAULT_PITCH_CHANCE = 2;

    public NormalFrame() {
        this.pitches = new Pitches();
    }

    @Override
    public Boolean isEndedFrame() {
        return this.pitches.getSize() == DEFAULT_PITCH_CHANCE || this.pitches.hasStrike();
    }

    @Override
    public Boolean isFinalFrame() {
        return false;
    }

    @Override
    public void add(int count) {
        pitches.add(count);
    }

    @Override
    public int firstPitch() {
        return this.pitches.firstPitch();
    }

    @Override
    public int secondPitch() {
        return this.pitches.secondPitch();
    }

    @Override
    public Pitches pitches() {
        return this.pitches;
    }
}
