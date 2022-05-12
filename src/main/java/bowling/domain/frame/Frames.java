package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frames {

    private static final int INITIAL_NORMAL_FRAME_NO = 1;
    private static final int NORMAL_FRAME_MAX_SIZE = 9;

    private final List<Frame> normalFrames = new ArrayList<>();
    private Frame finalFrame;

    public Frames() {
    }

    public void addNormalFrame(int firstNo, int secondNo) {
        if (normalFrames.size() == NORMAL_FRAME_MAX_SIZE) {
            throw new IllegalStateException("normal frame is full. max size is " + NORMAL_FRAME_MAX_SIZE);
        }
        if (normalFrames.isEmpty()) {
            normalFrames.add(new NormalFrame(INITIAL_NORMAL_FRAME_NO, firstNo, secondNo));
            return;
        }
        normalFrames.add(getLastFrame().next(firstNo, secondNo));
    }

    public void addFinalFrame(int firstNo, int secondNo) {
        if (normalFrames.size() != NORMAL_FRAME_MAX_SIZE) {
            throw new IllegalStateException("normal frame has to be full to set final frame");
        }
        finalFrame = getLastFrame().next(firstNo, secondNo);
    }

    public void addExtra(int extraNo) {
        if (finalFrame == null) {
            throw new IllegalStateException("final frame must be set to add extra");
        }
        if (finalFrame.getClass() != FinalFrame.class) {
            throw new IllegalStateException("last frame must be final frame to add extra");
        }
        ((FinalFrame) finalFrame).addExtra(extraNo);
    }

    private Frame getLastFrame() {
        return normalFrames.get(normalFrames.size() - 1);
    }

    public List<Frame> getNormalFrames() {
        return new ArrayList<>(normalFrames);
    }

    public Optional<Frame> getFinalFrame() {
        return Optional.ofNullable(finalFrame);
    }
}
