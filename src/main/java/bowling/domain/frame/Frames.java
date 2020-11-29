package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;
    private final List<Frame> frames;
    private Integer currentFrameNumber;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentFrameNumber = 1;
    }

    public static Frames create() {
        return new Frames(createFrames());
    }

    private static List<Frame> createFrames() {
        List<Frame> frames = Stream.iterate(Frame.first(), Frame::next)
                .limit(MAX_FRAME_NUMBER - 1)
                .collect(Collectors.toList());
        frames.add(LastFrame.empty());
        return frames;
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

    public List<Integer> calculateScores() {
        List<Integer> calculatedScores = new ArrayList<>();
        Integer previousFrameScore = 0;
        for (int frameNumber = MIN_FRAME_NUMBER; frameNumber <= MAX_FRAME_NUMBER; frameNumber++) {
            previousFrameScore = calculateScore(frameNumber, previousFrameScore);
            calculatedScores.add(previousFrameScore);
        }
        return calculatedScores;
    }

    private Integer calculateScore(int frameNumber, Integer previousFrameScore) {
        return Optional.ofNullable(previousFrameScore)
                .map(previousScore -> getFrame(frameNumber).calculateScore(previousScore, getNextFrames(frameNumber)))
                .orElse(null);
    }

    private List<Frame> getNextFrames(int frameNumber) {
        return frames.subList(frameNumber, Math.min(frameNumber + 2, MAX_FRAME_NUMBER));
    }

    private Frame getFrame(int frameNumber) {
        return frames.get(frameNumber - 1);
    }
}
