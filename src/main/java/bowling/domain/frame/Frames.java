package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public final class Frames {

    private static final int LAST_FRAME_NUMBER = 10;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of() {
        return new Frames(new ArrayList<>());
    }

    public static Frames of(final List<Frame> frames) {
        return new Frames(new ArrayList<>(frames));
    }

    public void add(final int knockDownCount) {
        if (frames.isEmpty()) {
            initAndInputFirstFrame(knockDownCount);
            return;
        }
        inputFrame(knockDownCount);
    }

    private void initAndInputFirstFrame(final int knockDownCount) {
        Frame frame = NormalFrame.of(NormalFrame.DEFAULT_ROUND_NUMBER, knockDownCount);
        frames.add(frame);
    }

    private void inputFrame(final int knockDownNumber) {
        Frame frame = lastFrame().nextFrame();
        frame.inputKnockDownNumber(knockDownNumber);
        if (!frames.contains(frame)) {
            frames.add(frame);
        }
    }

    public int nextTurnRoundNumber() {
        if (frames.isEmpty()) {
            return NormalFrame.DEFAULT_ROUND_NUMBER;
        }
        if (isLastFrameFinished()) {
            return lastFrameRoundNumber() + 1;
        }
        return lastFrameRoundNumber();
    }

    private boolean isLastFrameFinished() {
        return lastFrame().isFinished();
    }

    private int lastFrameRoundNumber() {
        return lastFrame().getRoundNumber();
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isFinished() {
        if (frames.isEmpty()) {
            return false;
        }
        if(lastFrameRoundNumber() != LAST_FRAME_NUMBER) {
            return false;
        }
        return isLastFrameFinished();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}