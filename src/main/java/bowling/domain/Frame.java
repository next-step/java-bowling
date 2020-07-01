package bowling.domain;

public abstract class Frame {
    protected static final int FIRST_FRAME = 1;
    protected static final int FINAL_FRAME = 10;
    protected int frameNo;
    protected Pitch pitch;
    protected Frame nextFrame;

    public Frame next() {
        if (frameNo == FINAL_FRAME) {
            throw new IllegalArgumentException("10 Frame is last frame");
        }
        if (frameNo + 1 == FINAL_FRAME) {
            nextFrame = new FinalFrame();
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameNo + 1);
        return nextFrame;
    }

    public abstract State bowling(Pin pin);

    public abstract ShotHistory getShotHistory();

    public abstract boolean isGameEnd();
}
