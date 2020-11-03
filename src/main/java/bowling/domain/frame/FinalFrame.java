package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.state.*;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static final String FRAME = "%-3s";
    private final int frameNumber;
    private final State state;

    public static FinalFrame init() {
        return new FinalFrame(10, new Ready());
    }

    public FinalFrame(final int frameNumber, final State state) {
        if (frameNumber != 10) {
            throw new IllegalArgumentException("마지막 프레임 넘버는 10 입니다.");
        }
        this.frameNumber = frameNumber;
        this.state = state;
    }

    @Override
    public boolean isFinished() {
        return state.isFinish();
    }

    @Override
    public Frame bowl(final Pins pins) {
        if (isFinished()) {
            throw new IllegalStateException(String.format("이미 완료된 %d 프레임 입니다.", frameNumber));
        }

        State result = state.bowl(pins);
        if (result instanceof Strike || result instanceof Spare) {
            return new FinalFrame(frameNumber, Bonus.start(result));
        }
        return new FinalFrame(frameNumber, result);
    }

    @Override
    public String print() {
        return String.format(FRAME, state.print());
    }

    @Override
    public int number() {
        return frameNumber;
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;
        final FinalFrame that = (FinalFrame) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
