package bowling.step4.domain.frame;

import bowling.step4.domain.Pitches;

public abstract class Frame {

    final Pitches pitches;

    protected Frame() {
        this.pitches = new Pitches();
    }

    public abstract Boolean isEndedFrame();

    public abstract Boolean isFinalFrame();


    public void add(int count) {
        pitches.add(count);
    }

    public int firstPitch() {
        return this.pitches.firstPitch();
    }

    public int secondPitch() {
        return this.pitches.secondPitch();
    }

    public Pitches pitches() {
        return this.pitches;
    }
}
