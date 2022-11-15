package bowling.step3.domain;

public abstract class Frame {

    final Pitches pitches;

    public Frame() {
        this.pitches = new Pitches();
    }

    public abstract Boolean isEndedFrame();

    public void add(int count){
        pitches.add(count);
    }

    public Pitches pitches() {
        return pitches;
    }
}
