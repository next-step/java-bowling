package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.global.utils.ExceptionMessage;

public class FinalFrame implements Frame {

    private int number;
    private boolean next;

    private FinalFrame(int number, boolean next) {
        this.number = number;
        this.next = next;
        validateFinalFrameRange();
    }

    public static Frame finalFrame() {
        return new FinalFrame(Frames.TOTAL_FRAME, true);
    }

    public void validateFinalFrameRange() {
        if (number != Frames.TOTAL_FRAME) {
            throw new OutOfFrameRangeException(ExceptionMessage.INVALID_FINAL_FRAME_NUMBER);
        }
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
