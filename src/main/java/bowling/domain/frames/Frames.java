package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishGameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Frames {

    private List<Frame> frames;

    public Frames() {
        this(init());
    }

    public Frames(final List<Frame> frames) {
        this.frames = frames;
    }

    private static List<Frame> init() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame());
        }
        frames.add(new FinalFrame());
        return frames;
    }

    public void roll(final Score score) {
        checkFinishGame();

        Frame currentFrame = currentFrame();
        currentFrame.roll(score);
    }

    private void checkFinishGame() {
        if (this.isFinish()) {
            throw new FinishGameException();
        }
    }

    public int lastFinishIndex() {
        Frame currentFrame = currentFrame();
        return frames.indexOf(currentFrame);
    }

    private int score(final int index) {

        Frame currentFrame = this.frames.get(index);
        Frame nextFrame = this.frames.get(index + 1);

        if (!currentFrame.isFinish()) {
            return 0;
        }

        if (!nextFrame.isFinish()) {
            if (currentFrame.isSpare()) {
                if (nextFrame.isInProgress() && !nextFrame.isStrike()) {
                    return currentFrame.total(nextFrame);
                }
                return 0;
            }
            if (currentFrame.isStrike()) {
                return 0;
            }
        }
        if (currentFrame.isStrike() && nextFrame.isStrike()) {
            return 0;
        }

        return currentFrame.total(nextFrame);
    }

    public int total(final int index) {
        int total = IntStream.range(0, index)
                .map(this::score)
                .sum();

        Frame currentFrame = this.frames.get(index);
        Frame nextFrame = this.frames.get(index + 1);

        return total + currentFrame.total(nextFrame);
    }

    private Frame currentFrame() {
        return frames.stream()
                .filter(frame -> !frame.isFinish())
                .findFirst()
                .orElseThrow(FinishGameException::new);
    }

    public boolean isFinish() {
        return this.frames.stream().allMatch(Frame::isFinish);
    }

    public List<Frame> elements() {
        return Collections.unmodifiableList(this.frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
