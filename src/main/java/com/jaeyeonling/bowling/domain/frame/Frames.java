package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int START_INDEX = 0;
    private static final int DEFAULT_INDEX = 1;
    private static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames = new ArrayList<>();
    private int currentIndex;

    public Frames() {
        Frame prevFrame = initializeFinalFrame();
        for (int i = START_INDEX; i < NORMAL_FRAME_COUNT; i++) {
            prevFrame = initializeNormalFrame(prevFrame);
        }

        Collections.reverse(frames);
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

    private Frame initializeFinalFrame() {
        final Frame finalFrame = new FinalFrame();
        frames.add(finalFrame);

        return finalFrame;
    }

    private Frame initializeNormalFrame(Frame prevFrame) {
        prevFrame = new NormalFrame(prevFrame);
        frames.add(prevFrame);

        return prevFrame;
    }

    private void incrementCurrentIndex() {
        currentIndex++;
    }
}
