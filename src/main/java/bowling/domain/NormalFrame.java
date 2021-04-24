package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int NORMAL_FRAME_INDEX_MAX = 8;

    private final int index;
    private final PinCounts pinCounts;

    public NormalFrame(int index) {
        this(index, new NormalPinCounts());
    }

    public NormalFrame(int index, PinCounts FrameBowls) {
        this.index = index;
        this.pinCounts = FrameBowls;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (pinCounts.pinCounts().isEmpty()) {
            return addBowl(pinCount);
        }

        return addBowl(pinCount);
    }

    public Frame throwBowl(int pinCount) {
        return throwBowl(String.valueOf(pinCount));
    }

    private Frame addBowl(int pinCount) {
        pinCounts.knockDown(pinCount);
        return new NormalFrame(index, pinCounts);
    }

    private Frame addBowl(String pinCount) {
        return addBowl(Integer.parseInt(pinCount));
    }

    @Override
    public Frame next() {
        if (isFinished() && index < NORMAL_FRAME_INDEX_MAX) {
            return new NormalFrame(index + 1, new NormalPinCounts());
        }

        if (isFinished() && index == NORMAL_FRAME_INDEX_MAX) {
            return new FinalFrame();
        }

        return this;
    }

    @Override
    public boolean isFinished() {
        return pinCounts.isFinished();
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public PinCounts pinCounts() {
        return pinCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(pinCounts, that.pinCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pinCounts);
    }
}
