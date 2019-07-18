package com.jaeyeonling.bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FramesFactory {

    private static final int START_INDEX = 0;

    public static Frames create() {
        return new Frames(initializeFrames());
    }

    private static List<Frame> initializeFrames() {
        final List<Frame> frames = new ArrayList<>();

        final Frame finalFrame = initializeFinalFrame(frames);
        initializeNormalFrames(finalFrame, frames);

        Collections.reverse(frames);

        return frames;
    }

    private static Frame initializeFinalFrame(final List<Frame> frames) {
        final Frame finalFrame = new FinalFrame();
        frames.add(finalFrame);

        return finalFrame;
    }

    private static void initializeNormalFrames(final Frame finalFrame,
                                               final List<Frame> frames) {
        Frame prevFrame = finalFrame;
        for (int i = START_INDEX; i < Frames.NORMAL_FRAME_COUNT; i++) {
            final Frame normalFrame = new NormalFrame(prevFrame);
            frames.add(normalFrame);

            prevFrame = normalFrame;
        }
    }
}
