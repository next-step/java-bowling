package bowling.domain.frame;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Frames {

    private final Frame firstFrame;

    private Frames(Frame firstFrame) {
        this.firstFrame = firstFrame;
    }

    public static Frames initialize() {
        final Frame firstFrame = NormalFrame.createFirstFrame();

        Frame currentFrame = firstFrame;
        while (!currentFrame.isFinalFrame()) {
            currentFrame.createNextFrame();
            currentFrame = currentFrame.nextFrame();
        }
        return new Frames(firstFrame);
    }

    public boolean isEnded(RoundNumber roundNumber) {
        return getFrame(roundNumber).isEnded();
    }

    public void knockDownPin(RoundNumber roundNumber, Pin pin) {
        getFrame(roundNumber).knockDownPin(pin);
    }

    public Frame getFrame(RoundNumber roundNumber) {
        Frame currentFrame = firstFrame;
        while (!currentFrame.roundNumberEquals(roundNumber)) {
            currentFrame = currentFrame.nextFrame();
        }
        return currentFrame;
    }

    public List<Frame> value() {
        List<Frame> frames = new ArrayList<>();

        Frame currentFrame = this.firstFrame;
        while (!currentFrame.isFinalFrame()) {
            frames.add(currentFrame);
            currentFrame = currentFrame.nextFrame();
        }
        frames.add(currentFrame);

        return Collections.unmodifiableList(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames = (Frames) o;
        return Objects.equals(firstFrame, frames.firstFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFrame);
    }
}
