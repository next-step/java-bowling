package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.controller.BowlingGame.*;

public class Frames {

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public int frameNumber() {
        return frames.size();
    }

    public boolean finished() {
        return frames.size() == LAST_FRAME && !currentFrame().continuable();
    }

    public void pitch(int pins) {
        addAdditionalPins(pins);

        Frame currentFrame = currentFrame();
        Frame nextFrame = currentFrame.frameAfterPitch(pins);

        if (currentFrame == nextFrame || finished()) {
            return;
        }

        if (frames.size() == NINTH_FRAME) {
            nextFrame = new FinalFrame();
        }

        frames.add(nextFrame);
    }

    private void addAdditionalPins(int pins) {
        frames.subList(0, currentIndex())
                .stream()
                .filter(Frame::remainderLeft)
                .forEach(frame -> frame.addPins(pins));
    }

    private Frame currentFrame() {
        return frames.get(currentIndex());
    }

    private int currentIndex() {
        return frames.size() - 1;
    }

    public String frameState(int frameNumber) {
        if (frames.size() < frameNumber) {
            throw new IndexOutOfBoundsException("That frame is not complete.");
        }

        return frames.get(frameNumber - 1).state();
    }
}
