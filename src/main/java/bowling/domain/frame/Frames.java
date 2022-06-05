package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.Scores;

import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int LAST_FRAME_NUMBER = 9;
    private static final int START_FRAME_NUMBER = 0;
    private static final int UNIT_FRAME_NUMBER = 1;

    private final List<Frame> frames;
    private int currentFrameNumber;

    public Frames(List<Frame> frames, int currentFrameNumber) {
        validateFramesAndFrameNumber(frames, currentFrameNumber);
        this.currentFrameNumber = currentFrameNumber;
        this.frames = frames;
    }

    private void validateFramesAndFrameNumber(List<Frame> frames, int currentFrameNumber) {
        validateFrames(frames);
        validateFrameNumber(currentFrameNumber);
    }

    private void validateFrames(List<Frame> frames) {
        if (frames == null) {
            throw new IllegalArgumentException("프레임 리스트는 null 일 수 없습니다.");
        }
    }

    private void validateFrameNumber(int currentFrameNumber) {
        if (currentFrameNumber > LAST_FRAME_NUMBER || currentFrameNumber < START_FRAME_NUMBER) {
            throw new IllegalArgumentException(String.format("프레임 넘버는 0 ~ 9를 벗어날 수 없습니다. 전달 받은 프레임 넘버 : %d", currentFrameNumber));
        }
    }

    public static Frames initialize() {
        return new Frames(FrameFactory.create(), START_FRAME_NUMBER);
    }

    public boolean isFinalFrameEnd() {
        return currentFrameNumber == LAST_FRAME_NUMBER && currentFrame().isFrameEnd();
    }

    public boolean isCurrentFrameEnd() {
        return currentFrame().isFrameEnd();
    }

    public void bowl(Pins hitPins) {
        Frame currentFrame = currentFrame();
        currentFrame.bowl(hitPins);
    }

    public void updateToNextFrameNumber() {
        if (this.currentFrameNumber == LAST_FRAME_NUMBER) {
            return;
        }
        this.currentFrameNumber += UNIT_FRAME_NUMBER;
    }

    private Frame currentFrame() {
        return frames.get(currentFrameNumber);
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
        score = nextFrameAddScore(score, currentFrameNumber);
        return score;
    }

    private Score nextFrameAddScore(Score score, int currentFrameNumber) {
        while (!score.isNotCalculable()) {
            currentFrameNumber = getNextFrameNumber(currentFrameNumber);
            Frame currentFrame = this.frames.get(currentFrameNumber);
            score = currentFrame.calculateAdditionalScore(score);
        }
        return score;
    }

    private int getNextFrameNumber(int currentFrameNumber) {
        if (currentFrameNumber == LAST_FRAME_NUMBER) {
            return currentFrameNumber;
        }
        return currentFrameNumber + UNIT_FRAME_NUMBER;
    }

    public List<Frame> frames() {
        return frames;
    }

    public boolean isCurrentFrameNumber(int currentFrameNumber) {
        return this.currentFrameNumber == currentFrameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frames frames1 = (Frames) o;

        if (currentFrameNumber != frames1.currentFrameNumber) return false;
        return frames.equals(frames1.frames);
    }

    @Override
    public int hashCode() {
        int result = frames.hashCode();
        result = 31 * result + currentFrameNumber;
        return result;
    }
}