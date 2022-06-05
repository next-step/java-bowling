package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.Scores;

import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final List<Frame> frames;
    private FrameNumber currentFrameNumber;

    public Frames(List<Frame> frames, FrameNumber frameNumber) {
        validateFramesAndFrameNumber(frames, frameNumber);
        this.currentFrameNumber = frameNumber;
        this.frames = frames;
    }

    private void validateFramesAndFrameNumber(List<Frame> frames, FrameNumber frameNumber) {
        if (frames == null) {
            throw new IllegalArgumentException("프레임 리스트는 null 일 수 없습니다.");
        }
        if (frameNumber == null) {
            throw new IllegalArgumentException("프레임 넘버는 null 일 수 없습니다.");
        }
    }

    public static Frames initialize() {
        return new Frames(FrameFactory.create(), new FrameNumber(FrameNumber.START_FRAME_NUMBER));
    }

    public boolean isFinalFrameEnd() {
        return currentFrameNumber.isLast() && currentFrame().isFrameEnd();
    }

    public void bowl(Pins hitPins) {
        Frame currentFrame = currentFrame();
        currentFrame.bowl(hitPins);
    }

    public void nextFrame() {
        if (currentFrame().isFrameEnd()) {
            updateNextFrameNumber();
        }
    }

    private void updateNextFrameNumber() {
        if (this.currentFrameNumber.isLast()) {
            return;
        }
        this.currentFrameNumber = this.currentFrameNumber.nextFrameNumber();
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber.frameNumber();
    }

    private Frame currentFrame() {
        return frames.get(currentFrameNumber.frameNumber());
    }

    public List<Integer> accumulateScores() {
        return scores().accumulateScore();
    }

    private Scores scores() {
        List<Score> scores = frames.stream()
                .filter(Frame::isFrameEnd)
                .map(this::calculateAdditionalScore)
                .filter(score -> !score.isNotAvailableScore())
                .collect(Collectors.toList());
        return new Scores(scores);
    }

    private Score calculateAdditionalScore(Frame frame) {
        Frame currentFrame = frame;
        int currentFrameNumber = this.frames.indexOf(currentFrame);
        Score score = currentFrame.score();
        score = nextFrameAddScore(new FrameNumber(currentFrameNumber), score);
        return score;
    }

    private Score nextFrameAddScore(FrameNumber currentFrameNumber, Score score) {
        while (!score.isNotCalculable()) {
            currentFrameNumber = getNextFrameNumber(currentFrameNumber);
            Frame currentFrame = this.frames.get(currentFrameNumber.frameNumber());
            score = currentFrame.calculateAdditionalScore(score);
        }
        return score;
    }

    private FrameNumber getNextFrameNumber(FrameNumber currentFrameNumber) {
        if (currentFrameNumber.isLast()) {
            return currentFrameNumber;
        }
        return currentFrameNumber.nextFrameNumber();
    }

    public List<Frame> frames() {
        return frames;
    }

    public boolean isCurrentFrameNumber(FrameNumber currentFrameNumber) {
        return this.currentFrameNumber.equals(currentFrameNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frames frames1 = (Frames) o;

        if (!frames.equals(frames1.frames)) return false;
        return currentFrameNumber.equals(frames1.currentFrameNumber);
    }

    @Override
    public int hashCode() {
        int result = frames.hashCode();
        result = 31 * result + currentFrameNumber.hashCode();
        return result;
    }
}