package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    private static final int MAX_FRAME_NUMBER = 10;
    private final List<Frame> frames;
    private int currentFrameNumber;

    public Frames(List<Frame> frames) {
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
        Frame currentFrame = frames.get(currentFrameNumber - 1);
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
}
