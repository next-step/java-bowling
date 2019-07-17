package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.count.Count;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

import java.util.Collections;
import java.util.List;

public class Frames {

    static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;
    private Count indexCount;

    Frames(final List<Frame> frames) {
        this.frames = frames;
        indexCount = Count.of();
    }

    public void bowl(final KnockdownPins knockdownPins) {
        final Frame currentFrame = getCurrentFrame();

        currentFrame.bowl(knockdownPins);

        if (currentFrame.isFinished()) {
            incrementIndex();
        }
    }

    public boolean isFinished() {
        return indexCount.isHigherAndEquals(NORMAL_FRAME_COUNT) && getCurrentFrame().isFinished();
    }

    public int getCurrentFrameIndex() {
        return indexCount.up().getCount();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private Frame getCurrentFrame() {
        return frames.get(getCurrentIndex());
    }

    private int getCurrentIndex() {
        return Math.min(indexCount.getCount(), NORMAL_FRAME_COUNT);
    }

    private void incrementIndex() {
        indexCount = indexCount.up();
    }
}
