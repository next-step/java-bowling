package bowling.domian.frame;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;
    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame bowl(int falledPinsCount) {
        return null;
    }

    public boolean isFrameEnd() {
        return false;
    }
}
