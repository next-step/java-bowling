package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Frames {
    private static final int MAX_FRAME_NUMBER = 10;
    private final List<Frame> frames;
    private int currentFrameNumber;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentFrameNumber = 1;
    }

    public static Frames create() {
        return new Frames(createFrames());
    }

    private static List<Frame> createFrames() {
        return Stream.iterate(Frame.first(), Frame::next)
                .limit(MAX_FRAME_NUMBER)
                .collect(Collectors.toList());
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }

    public boolean isFinished() {
        return currentFrameNumber > MAX_FRAME_NUMBER;
    }

    public void record(int score) {
        validateRecordPossible();
        Frame currentFrame = getFrame(currentFrameNumber);
        currentFrame.record(score);
        increaseCurrentFrameNumber(currentFrame);
    }

    private void validateRecordPossible() {
        if (isFinished()) {
            throw new InvalidFrameRecordActionException();
        }
    }

    private void increaseCurrentFrameNumber(Frame currentFrame) {
        if (currentFrame.isFinished()) {
            currentFrameNumber += 1;
        }
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public List<Integer> calculateScore() {
        List<Integer> calculatedScores = IntStream.range(1, MAX_FRAME_NUMBER + 1)
                .boxed()
                .map(i -> (Integer) null)
                .collect(Collectors.toList());
        int previousFrameScore = 0;
        for (int frameNumber = 1; frameNumber <= MAX_FRAME_NUMBER; frameNumber++) {
            Integer calculatedScore = calculateScore(frameNumber, previousFrameScore);
            if (calculatedScore == null) {
                break;
            }
            calculatedScores.set(frameNumber - 1, calculatedScore);
            previousFrameScore = calculatedScore;
        }
        return calculatedScores;
    }

    private Integer calculateScore(int frameNumber, int previousFrameScore) {
        if (frameNumber > currentFrameNumber) {
            return null;
        }
        if (needNextScores(frameNumber)) {
            return calculateScoreWithNext(frameNumber, previousFrameScore);
        }
        return previousFrameScore + sumScores(frameNumber);
    }

    private Integer calculateScoreWithNext(int frameNumber, int previousFrameScore) {
        if (hasNoNextScores(frameNumber)) {
            return null;
        }
        return previousFrameScore + sumScores(frameNumber) + getNextScores(frameNumber);
    }

    private int getNextScores(int frameNumber) {
        int nextScoresCount = getFrame(frameNumber).getNecessaryNextScoresCount();
        if (nextScoresCount == 1) {
            return getScore(frameNumber + 1, 1);
        }
        if (nextScoresCount == 2) {
            return getNextTwoScores(frameNumber);
        }
        return 0;
    }

    private int getNextTwoScores(int frameNumber) {
        if (getFrame(frameNumber + 1).getScoresCount() == 1) {
            return getScore(frameNumber + 1, 1) + getScore(frameNumber + 2, 1);
        }
        return getScore(frameNumber + 1, 1) + getScore(frameNumber + 1, 2);
    }

    private boolean needNextScores(int frameNumber) {
        return getFrame(frameNumber).needNextScores();
    }

    private boolean hasNoNextScores(int frameNumber) {
        return getFrame(frameNumber).getNecessaryNextScoresCount() > getNextScoresCount(frameNumber);
    }

    private int getNextScoresCount(int frameNumber) {
        if (frameNumber == MAX_FRAME_NUMBER) {
            return 0;
        }
        if (frameNumber == MAX_FRAME_NUMBER - 1) {
            return getScoresCount(frameNumber + 1);
        }
        return getScoresCount(frameNumber + 1)
                + getScoresCount(frameNumber + 2);
    }

    private int getScoresCount(int frameNumber) {
        return getFrame(frameNumber).getScoresCount();
    }

    private int getScore(int frameNumber, int tryCount) {
        return getFrame(frameNumber).getScore(tryCount);
    }

    private int sumScores(int frameNumber) {
        return getFrame(frameNumber)
                .getScores()
                .stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    private Frame getFrame(int frameNumber) {
        return frames.get(frameNumber - 1);
    }
}
