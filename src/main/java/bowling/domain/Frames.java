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
        frames.add(NormalFrame.first());
        return new Frames(frames);
    }

    public Frame get(int index) {
        if(index >= frames.size()) {
            return NullFrame.getInstance();
        }

        return frames.get(index);
    }

    public void bowl(int value) {
        Frame frame = fetchTargetFrame();
        frame.add(value);
        addNewFrameIfOver(frame);
    }

    private void addNewFrameIfOver(Frame frame) {
        if(frame.isOver()) {
            frames.add(frame.next());
        }
    }

    private Frame fetchTargetFrame() {
        return frames.stream()
                .filter(frame -> !frame.isOver())
                .findFirst()
                .orElseThrow(NoRemainingFrameException::new);
    }
}
