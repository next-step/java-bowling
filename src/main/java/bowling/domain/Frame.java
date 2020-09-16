package bowling.domain;

import java.util.List;

public class Frame {

    public static final int LAST_FRAME_NUMBER = 10;

    protected int number;
    protected State state;

    protected Frame(int number) {
        this.number = number;
    }

    public Frame hit(int count) {
        if (state == null) {
            state = State.from(count);
            return this;
        }

        if (IsNextLastFrame()) {
            Frame lastFrame = LastFrame.from();
            lastFrame.hit(count);

            return lastFrame;
        }

        if (isFinish()) {
            Frame next = new Frame(nextFrameNumber());
            next.hit(count);
            return next;
        }

        state = state.roll(count);
        return this;
    }

    private boolean IsNextLastFrame() {
        return isFinish() && nextFrameNumber() == LAST_FRAME_NUMBER;
    }

    public Frame next() {
        if (!hasNextFrame()) {
            throw new RuntimeException("다음 프레임이 존재하지 않습니다.");
        }

        int nextFrameNumber = nextFrameNumber();
        return nextFrameNumber == LAST_FRAME_NUMBER ? LastFrame.from() : NormalFrame.of(nextFrameNumber);
    }

    public boolean hasNextFrame() {
        return nextFrameNumber() <= LAST_FRAME_NUMBER;
    }

    private int nextFrameNumber() {
        return number + 1;
    }

    public boolean isFinish() {
        if (state == null) {
            return false;
        }

        return state.isFinish();
    }

    public int getNumber() {
        return number;
    }

    public List<String> value() {
        return state.getValue();
    }

    public Score getScore() {
        return state.getScore();
    }

    public Score sumScore(Score before) {
        if (before.canNextSum()) {
            state.sumScore(before);
        }
        return before;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "number=" + number +
                ", state=" + state +
                '}';
    }
}