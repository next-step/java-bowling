package bowling.domain;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    public FinalFrame() {
        super(new FinalPitch(), States.newInstance());
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame next() {
        return this;
    }
}
