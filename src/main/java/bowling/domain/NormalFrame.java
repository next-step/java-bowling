package bowling.domain;

public class NormalFrame extends Frame {

    private static final int EMPTY_FRAME = 0;
    private static final int FULL_FRAME = 2;
    private static final int FINAL_NORMAL_FRAME = 9;

    public boolean hasSecond() {
        return !pinNumbers.index(0).isStrike();
   }

    @Override
    public boolean hasNext() {
        if (pinNumbers.size() == FULL_FRAME) {
            return false;
        }
        return pinNumbers.size() == EMPTY_FRAME || hasSecond();
    }

    @Override
    public FrameStrategy nextFrame(int frameNumber) {
        if (frameNumber == FINAL_NORMAL_FRAME) {
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame();
        return next;
    }
}
