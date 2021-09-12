package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class Frames {

    private static final int LAST_FRAME_NUMBER = 10;

    private final LinkedList<Frame> frames;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of() {
        return new Frames(new LinkedList<>());
    }

    public static Frames of(final List<Frame> frames) {
        return new Frames(new LinkedList<>(frames));
    }

    public void add(final int knockDownCount) {
        if (frames.isEmpty()) {
            initAndInputFirstFrame(knockDownCount);
            return;
        }
        inputFrame(knockDownCount);
    }

    private void initAndInputFirstFrame(final int knockDownCount) {
        Frame frame = NormalFrame.of(NormalFrame.FIRST_ROUND_NUMBER, knockDownCount);
        frames.add(frame);
    }

    private void inputFrame(final int knockDownNumber) {
        if (isLastFrameFinished()) {
            inputNextFrame(knockDownNumber);
            return;
        }
        inputCurrentFrame(knockDownNumber);
    }

    private void inputNextFrame(final int knockDownNumber) {
        frames.addLast(currentFrame().bowl(knockDownNumber));
    }

    private void inputCurrentFrame(final int knockDownNumber) {
        frames.set(getLastFameIndex(), currentFrame().bowl(knockDownNumber));
    }

    private Frame lastFrame() {
        return frames.getLast();
    }

    private Frame currentFrame() {
        return lastFrame().next();
    }

    private boolean isLastFrameFinished() {
        return lastFrame().isFinished();
    }

    private int getLastFameIndex() {
        return frames.lastIndexOf(lastFrame());
    }

    public int nextTurnRoundNumber() {
        if (frames.isEmpty()) {
            return NormalFrame.FIRST_ROUND_NUMBER;
        }
        if (isLastFrameFinished()) {
            return nextFrameRoundNumber();
        }
        return lastFrameRoundNumber();
    }

    private int nextFrameRoundNumber() {
        return lastFrameRoundNumber() + Frame.NEXT_ROUND_NUMBER_DISTANCE;
    }

    private int lastFrameRoundNumber() {
        return lastFrame().getRoundNumber();
    }

    public boolean isFinished() {
        if (frames.isEmpty()) {
            return false;
        }
        if (lastFrameRoundNumber() != LAST_FRAME_NUMBER) {
            return false;
        }
        return isLastFrameFinished();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<Frame> getCanCalculateFrames() {
        return frames.stream()
                .filter(Frame::canCalculateScore)
                .collect(Collectors.toList());
    }
}