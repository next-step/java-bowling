package bowling.domain;

public class NormalFrame implements Frame {
    private static final int FIRST_INDEX = 1;
    private static final int NEXT_INDEX = 1;

    private final int index;
    private final PitchesGroup pitchesGroup = new PitchesGroup();


    private NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    public Frame next() {
        if (pitchesGroup.isMovableToNextFrame() && index == 9) {
            return new FinalFrame();
        }
        return pitchesGroup.isMovableToNextFrame() ? new NormalFrame(index + NEXT_INDEX) : this;
    }

    @Override
    public void bowl(int hitCounts) {
        pitchesGroup.recordPitch(hitCounts);
    }

    public int getIndex() {
        return index;
    }
}
