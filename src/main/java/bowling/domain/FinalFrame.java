package bowling.domain;

import java.util.Objects;

import bowling.exception.InvalidFrameNumberException;
import bowling.exception.InvalidPlayCountException;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;
    private static final String FRAME_ERROR_MESSAGE = "Final Frame은 10프레임만 가능합니다.";
    private static final String INVALID_PLAY_ERROR_MESSAGE = "최대 3회까지만 투구 가능합니다.";

    public FinalFrame(int frameNumber) {
        super(frameNumber);
    }

    protected void validateFrameNumber(int frameNumber) {
        if (FINAL_FRAME_NUMBER != frameNumber) {
            throw new InvalidFrameNumberException(FRAME_ERROR_MESSAGE);
        }
    }

    protected void validatePlayCount() {
        if (playCount > 2) {
            throw new InvalidPlayCountException(INVALID_PLAY_ERROR_MESSAGE);
        }
    }

    protected int additionalScore(int leftPlayCount, int sumScore) {
        if (playCount == 0) return -1;

        if (leftPlayCount == 1) {
            return sumScore + pinStatus().firstPin();
        }

        if (leftPlayCount == 2 && playCount >= 2) {
            return sumScore + pinStatus().firstPin() + pinStatus().secondPin();
        }

        return -1;
    }

    public boolean isEndFrame() {
        return (playCount > 1 && pinStatus().firstPin() + pinStatus().secondPin() < 10) || playCount > 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinalFrame that = (FinalFrame) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
