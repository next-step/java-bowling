package bowling.domain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Records {
    private final List<Frame> frames = new ArrayList<>();

    public List<Frame> getFrames() {
        return frames;
    }

    public int getRecordCount() {
        return frames.size();
    }

    public void record(int downPintCount) {
        Frame readyFrame = getReadyFrame();
        readyFrame.record(downPintCount);
    }

    public boolean isEndLastFrame() {
        return getLastFrame().isEndFrame();
    }

    private Frame getReadyFrame() {
        Frame frame = getLastFrame();
        if (frame.isEndFrame()) {
            frame = getNewFrame();
            frames.add(frame);
        }
        return frame;
    }

    private Frame getNewFrame() {
        if (frames.size() == RuleConfig.NUMBER_OF_FRAME - 1) {
            return new FinalFrame();
        }
        return new Frame();
    }

    private Frame getLastFrame() {
        if (CollectionUtils.isEmpty(frames)) {
            Frame frame = new Frame();
            frames.add(frame);
            return frame;
        }
        return frames.get(frames.size() - 1);
    }

}
