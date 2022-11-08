package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BowlingGame {

    public static final int SIZE_OF_FRAMES = 10;

    private final List<BowlingGameFrame> frames;
    private int indexOfCurrentFrame;

    public BowlingGame() {
        this.frames = createInitFrames();
        this.indexOfCurrentFrame = 0;
    }

    private List<BowlingGameFrame> createInitFrames() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        IntStream.range(0, SIZE_OF_FRAMES - 1)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));
        frames.add(new FinalBowlingGameFrame());
        return frames;
    }

    public BowlingGameFrame get(int index) {
        return frames.get(index);
    }

    public void hit(int pins) {
        BowlingGameFrame currentFrame = getCurrentFrame();
        currentFrame.add(pins);
        if (currentFrame.isEnded()) {
            indexOfCurrentFrame++;
        }
    }

    public int getIndexOfCurrentFrame() {
        if (isEnded()) {
            throw new IllegalStateException("이미 종료된 게임입니다.");
        }
        return indexOfCurrentFrame;
    }

    public BowlingGameFrame getCurrentFrame() {
        return frames.get(getIndexOfCurrentFrame());
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
        return indexOfCurrentFrame == game.indexOfCurrentFrame && Objects.equals(frames, game.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, indexOfCurrentFrame);
    }

    @Override
    public String toString() {
        return "BowlingGame{" +
                "frames=" + frames +
                ", indexOfCurrentFrame=" + indexOfCurrentFrame +
                '}';
    }

}
