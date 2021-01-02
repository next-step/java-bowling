package bowling.domain;

import bowling.exception.NoRemainingFrameException;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int FIRST_FRAME_NO = 1;
    public static final int LAST_FRAME_NO = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();

        Frame frame = addFirstFrame(frames);
        frame = addMiddleFrames(frames, frame);
        addLastFrame(frames, frame);

        return new Frames(frames);
    }

    private static Frame addFirstFrame(List<Frame> frames) {
        Frame frame = NormalFrame.init();
        frames.add(frame);
        return frame;
    }

    private static Frame addMiddleFrames(List<Frame> frames, Frame frame) {
        for(int i = FIRST_FRAME_NO; i < LAST_FRAME_NO - 1; i++) {
            frame = frame.getNext();
            frames.add(frame);
        }
        return frame;
    }

    private static void addLastFrame(List<Frame> frames, Frame frame) {
        frames.add(frame.getNext());
    }

    public Frame get(int index) {
        return frames.get(index);
    }

    public void bowl(int pins) {
        Frame frame = fetchTargetFrame();
        frame.bowl(pins);
    }

    private Frame fetchTargetFrame() {
        return frames.stream()
                .filter(frame -> !frame.isOver())
                .findFirst()
                .orElseThrow(NoRemainingFrameException::new);
    }

}
