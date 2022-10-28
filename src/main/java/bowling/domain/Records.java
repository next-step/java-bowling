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
        Frame lastFrame = getLastFrame();
        if (lastFrame.isEndFrame()) {
            lastFrame = new Frame();
            frames.add(lastFrame);
        }
        lastFrame.record(downPintCount);
    }

    private Frame getLastFrame() {
        if (CollectionUtils.isEmpty(frames)) {
            Frame frame = new Frame();
            frames.add(frame);
            return frame;
        }
        return frames.get(frames.size() - 1);
    }

    public boolean isEndLastFrame() {
        return getLastFrame().isEndFrame();
    }
}
