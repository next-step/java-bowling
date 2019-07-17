package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int DEFAULT_INDEX = 1;

    static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;
    private int currentIndex;

    Frames(final List<Frame> frames) {
        this.frames = frames;
    }

    public void bowl(final KnockdownPins knockdownPins) {
        final Frame currentFrame = getCurrentFrame();

        currentFrame.bowl(knockdownPins);

        if (currentFrame.isFinished()) {
            incrementCurrentIndex();
        }
    }

    public boolean isFinished() {
        return currentIndex >= NORMAL_FRAME_COUNT && getCurrentFrame().isFinished();
    }

    public int getCurrentFrameIndex() {
        return currentIndex + DEFAULT_INDEX;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private Frame getCurrentFrame() {
        return frames.get(Math.min(currentIndex, NORMAL_FRAME_COUNT));
    }

    private void incrementCurrentIndex() {
        currentIndex++;
    }
}
