package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private static final int MAX_FRAMES_SIZE = 10;
    private static final int START_FRAME_NUMBER = 1;

    private final List<Frame> frames;

    public Frames(final List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new LinkedList<>(Arrays.asList(new NormalFrame(START_FRAME_NUMBER))));
    }

    public boolean isFramesEnd() {
        return frames.size() == MAX_FRAMES_SIZE && frames.get(currentFrameIndex()).isEnd();
    }

    private int currentFrameIndex() {
        return frames.size() - 1;
    }

    public Frame pitch(final int countOfPins) {
        Frame currentFrame = frames.get(currentFrameIndex());
        if (!currentFrame.isEnd()) {
            return frames.get(currentFrameIndex());
        }
        if (isFinal(currentFrameIndex() + 1)) {
            return new FinalFrame();
        }
        return new NormalFrame(currentFrameIndex() + 1);
    }

    public boolean isFinal(int frameNumber) {
        return frameNumber == 10;
    }
}