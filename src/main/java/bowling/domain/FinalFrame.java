package bowling.domain;

public class FinalFrame extends Frame {

    public FinalFrame() {
        super(new FinalFramePitches());
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
