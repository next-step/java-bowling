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
        if (frameNumber < MAX_FRAME) {
            frames.add(new NormalFrame());
        }
        if (frameNumber == MAX_FRAME) {
            frames.add(new FinalFrame());
        }
    }

    public void addHittingPinsAtCurrentFrame(int hittingPins) {
        getCurrentFrame().pitching(hittingPins);
    }

    private Frame getCurrentFrame() {
        return this.frames.get(frameNumber - 1);
    }

    public void moveNextFrame() {
        if (!getCurrentFrame().isPossiblePitching()) {
            this.frameNumber += 1;
        }
    }

    public boolean isNotFinalGame() {
        return frames.stream()
                .anyMatch(Frame::isPossiblePitching);
    }

    public Frame findFrame(int index) {
        return this.getFrames().get(index);
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
