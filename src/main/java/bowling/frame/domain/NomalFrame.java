package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.global.utils.ExceptionMessage;

public class NomalFrame implements Frame {

    private int number;
    private boolean next;

    private NomalFrame(int number, boolean next) {
        this.number = number;
        this.next = next;
        validateFrameRange();
    }

    public static Frame newFrame(int number) {
        return new NomalFrame(number, true);
    }

    public void validateFrameRange() {
        if (number < 1 || number > 9) {
            throw new OutOfFrameRangeException(ExceptionMessage.INVALID_NOMAL_FRAME_NUMBER);
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean clear() {
        return true;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
