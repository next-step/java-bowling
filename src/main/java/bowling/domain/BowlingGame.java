package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.Objects;

public class BowlingGame {

    public static final int SIZE_OF_FRAMES = 10;

    private final Frame frame;
    private int indexOfCurrentFrame;

    public BowlingGame() {
        this.frame = createFrame();
        this.indexOfCurrentFrame = 0;
    }

    private Frame createFrame() {
        Frame frame = new FinalFrame();
        for (int i = 0; i < SIZE_OF_FRAMES - 1; i++) {
            frame = new NormalFrame(frame);
        }
        return frame;
    }

    public Frame get(int index) {
        Frame frame = this.frame;
        for (int i = 0; i < index; i++) {
            frame = frame.getNextFrame();
        }
        return frame;
    }

    public void hit(int pins) {
        Frame currentFrame = getCurrentFrame();
        currentFrame.add(pins);
    }

    public void moveNextFrame() {
        indexOfCurrentFrame++;
    }

    public Frame getCurrentFrame() {
        validateState();
        return get(indexOfCurrentFrame);
    }

    private void validateState() {
        if (isEnded()) {
            throw new IllegalStateException("이미 종료된 게임입니다.");
        }
    }

    public int getRemainedPins() {
        return getCurrentFrame().getRemainedPins();
    }

    public boolean isEnded() {
        return indexOfCurrentFrame == SIZE_OF_FRAMES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame game = (BowlingGame) o;
        return indexOfCurrentFrame == game.indexOfCurrentFrame && Objects.equals(frame, game.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame, indexOfCurrentFrame);
    }

    @Override
    public String toString() {
        return "BowlingGame{" +
                "frame=" + frame +
                ", indexOfCurrentFrame=" + indexOfCurrentFrame +
                '}';
    }

}
