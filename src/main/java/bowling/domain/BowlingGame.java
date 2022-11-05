package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BowlingGame {

    public static final int SIZE_OF_FRAMES = 10;

    private final List<BowlingGameFrame> frames;
    private int indexOfCurrentFrame;

    public BowlingGame(List<BowlingGameFrame> frames) {
        validate(frames);
        this.frames = frames;
        this.indexOfCurrentFrame = IntStream.range(0, frames.size())
                .filter(i -> frames.get(i)
                        .isOnGoing())
                .findFirst()
                .orElse(SIZE_OF_FRAMES);
    }

    private void validate(List<BowlingGameFrame> frames) {
        if (frames.size() != SIZE_OF_FRAMES) {
            throw new IllegalArgumentException(String.format("프레임은 %d개로 구성되어 있어야 합니다.", SIZE_OF_FRAMES));
        }

        IntStream.range(1, frames.size())
                .forEach(i -> validate(frames, i));
    }

    private void validate(List<BowlingGameFrame> frames, int index) {
        if (existsOnGoingFrameAndEndedFrame(frames.get(index - 1), frames.get(index))) {
            throw new IllegalArgumentException("진행중인 프레임 다음에는 종료된 프레임이 있을 수 없습니다.");
        }
    }

    private boolean existsOnGoingFrameAndEndedFrame(BowlingGameFrame previousFrame, BowlingGameFrame frame) {
        return previousFrame.isOnGoing() && frame.isEnded();
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

    public BowlingGameFrame getCurrentFrame() {
        if (isEnded()) {
            throw new IllegalStateException("이미 종료된 게임입니다.");
        }
        return frames.get(indexOfCurrentFrame);
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
