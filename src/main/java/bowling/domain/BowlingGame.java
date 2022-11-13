package bowling.domain;

import java.util.Objects;

public class BowlingGame {

    public static final int SIZE_OF_FRAMES = 10;

    private final BowlingGameFrame frame;
    private int indexOfCurrentFrame;

    public BowlingGame() {
        this.frame = createFrame();
        this.indexOfCurrentFrame = 0;
    }

    private BowlingGameFrame createFrame() {
        BowlingGameFrame frame = new FinalBowlingGameFrame();
        for (int i = 0; i < SIZE_OF_FRAMES - 1; i++) {
            frame = new NormalBowlingGameFrame(frame);
        }
        return frame;
    }

    public BowlingGameFrame get(int index) {
        BowlingGameFrame frame = this.frame;
        for (int i = 0; i < index; i++) {
            frame = frame.getNextFrame();
        }
        return frame;
    }

    public void hit(int pins) {
        BowlingGameFrame currentFrame = getCurrentFrame();
        currentFrame.add(pins);
        if (currentFrame.isEnded()) {
            indexOfCurrentFrame++;
        }
    }

    public int getNumberOfCurrentFrame() {
        validateState();
        return indexOfCurrentFrame + 1;
    }

    public BowlingGameFrame getCurrentFrame() {
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
