package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.utils.Preconditions;

import java.util.Objects;

public class FrameNumber {
    private static final int START_FRAME_NUMBER = 1;
    private static final int FINAL_FRAME_NUMBER = 10;

    private final int frameNumber;

    private FrameNumber(int frameNumber) {
        Preconditions.checkMinimumSize(frameNumber, START_FRAME_NUMBER,
                                       String.format("볼링은 %d 프레임부터 시작입니다.", START_FRAME_NUMBER));
        Preconditions.checkMaximumSize(frameNumber, FINAL_FRAME_NUMBER,
                                       String.format("볼링은 %d 프레임이 종료입니다.", FINAL_FRAME_NUMBER));

        this.frameNumber = frameNumber;
    }

    public static FrameNumber from(int frameNumber) {
        return new FrameNumber(frameNumber);
    }

    @GetterForUI
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
