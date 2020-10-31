package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final String FRAME = "%3s";
    private static final int START_FRAME_NUMBER = 1;
    private static final int END_FRAME_NUMBER = 9;
    private final int frameNumber;
    private final State state;

    public static Frame init(final int frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    public NormalFrame(final int frameNumber, final State state) {
        if (frameNumber < START_FRAME_NUMBER || frameNumber > END_FRAME_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("frameNumber 범위가 올바르지 않습니다.%d~%d", START_FRAME_NUMBER, END_FRAME_NUMBER));
        }
        this.frameNumber = frameNumber;
        this.state = state;
    }

    @Override
    public boolean isFinished() {
        return state.isFinish();
    }

    @Override
    public Frame bowl(final int fallenPinCount) {
        if (state.isFinish()) {
            throw new IllegalStateException(String.format("이미 완료된 %d 프레임 입니다.", frameNumber));
        }
        return new NormalFrame(frameNumber, state.bowl(fallenPinCount));
    }

    @Override
    public String print() {
        return String.format(FRAME, state.print());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;
        final NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
