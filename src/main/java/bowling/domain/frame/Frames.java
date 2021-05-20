package bowling.domain.frame;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int INIT_FRAME = 0;
    private static final int LAST_NORMAL_FRAME = 9;
    private static final int FINAL_FRAME = 10;

    private int frameCount;
    private List<Frame> frames;

    public Frames() {
        frameCount = INIT_FRAME;
        frames = new ArrayList<>();
    }

    public void moveFrameIfNeeded() {
        if (!shouldMoveFrame()) {
            return;
        }
        if (frameCount == LAST_NORMAL_FRAME) {
            frames.add(new FinalFrame());
        }
        if (frameCount < LAST_NORMAL_FRAME) {
            frames.add(new NormalFrame());
        }
        frameCount++;
    }

    private boolean shouldMoveFrame() {
        if (frameCount == INIT_FRAME) {
            return true;
        }
        int frameIndex = toIndex(frameCount);
        return frames.get(frameIndex).isEnd() && frameCount < FINAL_FRAME;
    }

    public void bowl(int pin) {
        int frameIndex = toIndex(frameCount);
        if (isEnd()) {
            throw new CustomException(ErrorCode.INVALID_BOWL);
        }
        Frame frame = frames.get(frameIndex);
        frame.bowl(pin);
    }

    public void updateScores(int point) {
        frames.stream()
                .filter(frame -> !frame.endedScoring())
                .peek(frame -> frame.addPoint(point))
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        int frameIndex = toIndex(frameCount);
        return frameCount == FINAL_FRAME && frames.get(frameIndex).isEnd();
    }

    public int frameCount() {
        if (frames.isEmpty()) {
            return frames.size() + 1;
        }
        int frameIndex = toIndex(frameCount);
        Frame currentFrame = frames.get(frameIndex);
        if (currentFrame.isEnd()) {
            return frameCount + 1;
        }
        return frameCount;
    }

    private int toIndex(int value) {
        return value - 1;
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> frames() {
        return frames;
    }

    public int closedScores() {
        return (int)frames.stream()
                .filter(Frame::endedScoring)
                .count();
    }
}
