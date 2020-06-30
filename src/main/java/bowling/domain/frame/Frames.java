package bowling.domain.frame;

import bowling.utils.ElementFindUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int MAX_FRAME = 10;
    private static final int ONE = 1;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void createNextFrame() {
        if (CollectionUtils.isEmpty(frames)) {
            frames.add(NormalFrame.createFirstFrame());
            return;
        }

        NormalFrame normalFrame = (NormalFrame) ElementFindUtils.findLastElement(frames);
        if (isLast()) {
            frames.add(normalFrame.createLastFrame());
            return;
        }

        frames.add(normalFrame.createNextFrame());
        return;
    }

    private boolean isLast() {
        return MAX_FRAME - ONE == frames.size();
    }

    public int getFrameSize() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame findCurrentFrame() {
        return ElementFindUtils.findLastElement(frames);
    }
}
