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

    // 스트라이크 -> 이후의 두번의 슈팅이 있어야 점수 계산 완료
    // 스페어 -> 이후 한번의 슈팅이 있어야 점수 계산 완료
    // 슈팅 횟수를 알려면
    public boolean isScoresAddedUp(final int index) {
        Frame currentFrame = this.frames.get(index);

        int currentFrameAfterRollSize = currentFrame.numberOfRollAttempts();

        if (index <= 8) {
            Frame nextFrame = this.frames.get(index + 1);
            currentFrameAfterRollSize += nextFrame.numberOfRollAttempts();
        }
        if (index <= 7) {
            Frame nextNextFrame = this.frames.get(index + 2);
            currentFrameAfterRollSize += nextNextFrame.numberOfRollAttempts();
        }

        if ((currentFrame.isStrike() || currentFrame.isSpare()) && currentFrameAfterRollSize >= 3) {
            return true;
        }

        return !(currentFrame.isSpare() || currentFrame.isStrike()) && currentFrame.isFinish;
    }

    private int score(final int index) {
        Frame currentFrame = index >= 10 ? new FinalFrame() : frames.get(index);
        Frame nextFrame = index >= 9 ? new FinalFrame() : frames.get(index + 1);
        Frame nextNextFrame = index >= 8 ? new FinalFrame() : frames.get(index + 2);

        if (currentFrame.isStrike() && nextFrame.isStrike()) {
            return currentFrame.totalScore() + nextFrame.totalScore() + nextNextFrame.totalScore();
        }
        if (currentFrame.isStrike()) {
            return currentFrame.totalScore() + nextFrame.totalScore();
        }
        if (currentFrame.isSpare() && nextFrame.isStrike()) {
            return currentFrame.totalScore() + nextFrame.firstScore() + nextNextFrame.totalScore();
        }
        if (currentFrame.isSpare()) {
            return currentFrame.totalScore() + nextFrame.firstScore();
        }
        return currentFrame.totalScore();
    }

    public String total(final int index) {
        int sum = IntStream.rangeClosed(0, index)
                .map(this::score)
                .sum();
        return sum + "";
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
