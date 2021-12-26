package bowling.domain.frame;

import bowling.domain.FrameIndex;
import bowling.domain.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        validate(frames);
        this.frames = frames;
    }

    public static Frames initialize() {
        return new Frames(new ArrayList<>(Collections.singletonList(BasicFrame.initialize())));
    }

    public void bowl(Pins pins) {
        Frame lastFrame = lastFrame();
        Frame resultFrame = lastFrame.bowl(pins);
        if (isPossibleToCreate(lastFrame, resultFrame)) {
            frames.add(resultFrame);
        }
    }

    public int lastFrameIndex() {
        return lastFrame().getIndex();
    }

    public boolean hasNext() {
        return !(lastFrame().isEnd() && hasLastFrame());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private void validate(List<Frame> frames) {
        if (frames == null) {
            throw new IllegalArgumentException("전달된 프레임들이 null입니다.");
        }
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("전달된 프레임들이 비어있습니다.");
        }
    }

    private boolean isPossibleToCreate(Frame lastFrame, Frame resultFrame) {
        return lastFrame.isEnd() && !resultFrame.isEnd();
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    private boolean hasLastFrame() {
        return frames.size() == FrameIndex.MAX_INDEX;
    }
}
