package bowling.domain;

public abstract class Frame {
    public static final int FIRST_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    protected int frameNo;
    protected Pitch pitch;
    protected Frame nextFrame;

    public static NormalFrame first() {
        return new NormalFrame(FIRST_FRAME);
    }

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

    public abstract FrameState bowling(Pin pin);

    public abstract ShotHistory getShotHistory();

    public abstract Score calculateScore();

    public abstract Score calculateBonusScore(Shot shot);

    public abstract boolean isGameEnd();

    public Pitch getPitch() {
        return pitch;
    }
}
