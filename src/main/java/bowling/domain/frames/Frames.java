package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishGameException;

import java.util.*;
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

    public boolean isFinishAddingUpScores(final int index) {
        Frame currentFrame = this.frames.get(index);

        int rollCountSinceIncludingCurrentFrame = this.frames.subList(index, frames.size())
                .stream()
                .map(Frame::getScores)
                .map(Scores::size)
                .reduce(0, Integer::sum);

        if ((currentFrame.isStrike() || currentFrame.isSpare()) && rollCountSinceIncludingCurrentFrame >= 3) {
            return true;
        }

        return !(currentFrame.isSpare() || currentFrame.isStrike()) && currentFrame.isFinish();
    }

    private int score(final int index) {
        Frame currentFrame = this.frames.get(index);
        int totalScore = currentFrame.getScores().downPins();

        List<Frame> nextFrames = this.frames.subList(index + 1, this.frames.size());

        int bonusScore = nextFrames.stream()
                .map(Frame::getScores)
                .map(Scores::elements)
                .flatMap(Collection::stream)
                .limit(bonusScorePlusCount(currentFrame))
                .map(Score::getNumberOfPins)
                .reduce(0, Integer::sum);

        return totalScore + bonusScore;
    }

    private int bonusScorePlusCount(final Frame currentFrame) {
        if (currentFrame.isStrike()) {
            return 2;
        }
        if (currentFrame.isSpare()) {
            return 1;
        }
        return 0;
    }

    public int totalScore(final int endInclusive) {
        return IntStream.rangeClosed(0, endInclusive)
                .map(this::score)
                .sum();
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
