package bowling.domain;

import java.util.Objects;

/**
 * Created By mand2 on 2020-12-18.
 */
public abstract class Frame {

    public final static int MAX_SCORE = 10;

    protected final int index;
    protected Pitch firstPitch;
    protected Pitch secondPitch;

    protected Frame(int index) {
        this(index, Pitch.from(0), Pitch.from(0));
    }

    protected Frame(int index, Pitch firstPitch, Pitch secondPitch) {
        this.index = index;
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    // TODO frames 에서 처리할 것.
//    public static Frame firstFrame() {
//        return NormalFrame.of(1);
//    }
//
//    public Frame nextFrame() {
//        return NormalFrame.of(index + 1);
//    }
//
//    public static Frame finalFrame() {
//        return FinalFrame.of(10);
//    }

    public abstract void firstPitch(int score);

    public abstract int getLeft();

    public abstract void isStrike();

    public int getIndex() {
        return index;
    }

    public int getFirstPitch() {
        return firstPitch.getScore();
    }

    public int getSecondPitch() {
        return secondPitch.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return index == frame.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
