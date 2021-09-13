package bowling.domain;

public class NormalFrame extends Frame {

    public NormalFrame() {
        super(new NormalFramePitches());
    }

    @Override
    public boolean addPitchIfPossible(final Pitch pitch) {
        return pitches.add(pitch);
    }

    @Override
    public boolean isFull() {
        return pitches.isFull();
    }

    @Override
    public int score() {
        return pitches.score(nextFrame);
    }

}
