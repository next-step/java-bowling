package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {
    private static final int MAX_FRAME = 10;
    private static final int MIN_FRAME = 1;

    private int frameNumber = MIN_FRAME;
    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frame();
    }

    private void frame() {
        IntStream.range(MIN_FRAME, MAX_FRAME + 1)
                .forEach(i -> setFrames(i));
    }

    private void setFrames(int frame) {
        if (frame < MAX_FRAME) {
            frames.add(new NormalFrame(frame));
        }
        if (frame == MAX_FRAME) {
            frames.add(new FinalFrame());
        }
    }

    public boolean isNotFinalFrame() {
        return frames.stream()
                .anyMatch(i -> i.isBallCount());
    }

    public int frameNumber() {
        return frameNumber;
    }

    private Frame nowFrame() {
        return frames.get(frameNumber - 1);
    }

    public void add(int pin) {
        nowFrame().addPin(pin);
    }

    public void nextFrame() {
        if (!nowFrame().isBallCount()) {
            this.frameNumber++;
        }
    }

    public Frame findFrame(int index) {
        return frames.get(index);
    }

}
