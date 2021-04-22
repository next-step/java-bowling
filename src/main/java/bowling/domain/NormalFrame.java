package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int NORMAL_FRAME_INDEX_MAX = 8;

    private final int index;
    private final FrameBowls frameBowls;

    public NormalFrame(int index) {
        this(index, new NormalFrameBowls());
    }

    public NormalFrame(int index, FrameBowls FrameBowls) {
        this.index = index;
        this.frameBowls = FrameBowls;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (!frameBowls.isFirstBowlThrown()) {
            return firstBowl(pinCount);
        }

        return secondBowl(pinCount);
    }

    public Frame throwBowl(int pinCount) {
        return throwBowl(String.valueOf(pinCount));
    }

    private Frame firstBowl(int firstPinCount) {
        return new NormalFrame(index, frameBowls.firstThrow(firstPinCount));
    }

    private Frame firstBowl(String firstPinCount) {
        return firstBowl(Integer.parseInt(firstPinCount));
    }

    private Frame secondBowl(int secondPinCount) {
        return new NormalFrame(index, frameBowls.secondThrow(secondPinCount));
    }

    private Frame secondBowl(String secondPinCount) {
        return secondBowl(Integer.parseInt(secondPinCount));
    }

    @Override
    public Frame next() {
        if (isFinished() && index < NORMAL_FRAME_INDEX_MAX) {
            return new NormalFrame(index + 1, new NormalFrameBowls());
        }

        if (isFinished() && index == NORMAL_FRAME_INDEX_MAX) {
            return new FinalFrame();
        }

        return this;
    }

    @Override
    public boolean isFinished() {
        return frameBowls.isFirstBowlStrike() || isAllThrown();
    }

    private boolean isAllThrown() {
        return frameBowls.isFirstBowlThrown() && frameBowls.isSecondBowlThrown();
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public FrameBowls bowls() {
        return frameBowls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(frameBowls, that.frameBowls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, frameBowls);
    }

    @Override
    public String toString() {
        if (isAllNotThrown()) {
            return "      ";
        }

        if (frameBowls.isFirstBowlStrike() || isOnlyFirstThrown()) {
            return "  " + frameBowls.firstPinCount().toString() + "   ";
        }

        if (frameBowls.isSecondBowlSpare()) {
            return "  " + frameBowls.firstPinCount().toString() + "|" + "/" +" ";
        }

        return "  " + frameBowls.firstPinCount().toString() + "|" + frameBowls.secondPinCount().toString() + " ";
    }

    private boolean isAllNotThrown() {
        return !frameBowls.isFirstBowlThrown() && !frameBowls.isSecondBowlThrown();
    }

    private boolean isOnlyFirstThrown() {
        return frameBowls.isFirstBowlThrown() && !frameBowls.isSecondBowlThrown();
    }
}
