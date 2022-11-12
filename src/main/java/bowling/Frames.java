package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames(int size) {
        this.frames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.frames.add(new Frame());
        }
    }

    public FrameNumber pitching(FrameNumber frameNumber, int hitCount) {
        Frame frame = frames.get(frameNumber.retrieveIndexNumber());
        frame.hitBowlingPin(hitCount);
        if (frame.finishFrame()) {
            return FrameNumber.next(frameNumber);
        }
        return frameNumber;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public FrameNumber pitchingLast(FrameNumber frameNumber, int hitCount) {
        Frame frame = frames.get(frameNumber.retrieveIndexNumber());
        frame.hitBowlingPin(hitCount);
        if (frame.finishLastFrame()) {
            return FrameNumber.next(frameNumber);
        }

        if (frame.clearAllFrame()) {
            frame.chargeBowlingPin();
            return frameNumber;
        }
        return frameNumber;
    }
}
