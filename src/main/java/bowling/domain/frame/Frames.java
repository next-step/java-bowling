package bowling.domain.frame;

import bowling.domain.engine.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.domain.Constants.BOWLING_LAST_ROUND;

public class Frames {

    private final List<Frame> frameList;

    public Frames() {
        this.frameList = new ArrayList<>();
        frameList.add(FrameFactory.first());
    }

    public void bowl(final int downPins) {
        Frame frame = currentFrame();
        frame.bowl(downPins);

        if (frame.isNextFrame()) {
            frameList.add(next());
        }
    }

    private Frame next() {
        return FrameFactory.next(frameSize());
    }

    private int frameSize() {
        return frameList.size();
    }

    private int frameIndex() {
        return frameSize() - 1;
    }

    public boolean isLastFrame() {
        return (frameSize() == BOWLING_LAST_ROUND) && currentFrame().isEnd();
    }

    private Frame currentFrame() {
        return frameList.get(frameIndex());
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(frameList);
    }
}
