package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.IntStream.rangeClosed;

public class Frames {

    public static final int MIN_FRAME = 1;
    public static final int MAX_FRAME = 10;

    private int frameNumber;
    private final List<Frame> frames;

    public Frames() {
        frameNumber = MIN_FRAME;
        this.frames = new ArrayList<>();
        initFrames();
    }

    private void initFrames() {
        rangeClosed(MIN_FRAME, MAX_FRAME)
                .forEach(this::initFrame);
    }

    private void initFrame(int frameNumber) {
//        checkFrameNumber(frameNumber);
        if (frameNumber < MAX_FRAME) {
            frames.add(new NormalFrame());
        }
        if (frameNumber == MAX_FRAME) {
            frames.add(new FinalFrame());
        }
    }

//    private void checkFrameNumber(int frameNumber) {
//        if (!(MIN_FRAME <= frameNumber && frameNumber <= MAX_FRAME)) {
//            throw new IllegalArgumentException("");
//        }
//    }

    public void addPinsCurrentFrame(int hittingPins) {
        getCurrentFrame().addKnockDownPins(hittingPins);
    }

    private Frame getCurrentFrame() {
        return this.frames.get(frameNumber - 1);
    }

    public void moveNextFrame() {
        this.frameNumber += 1;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames that = (Frames) o;
        return frameNumber == that.frameNumber && Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, frames);
    }
}
