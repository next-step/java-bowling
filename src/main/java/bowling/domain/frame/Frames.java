package bowling.domain.frame;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initialize() {
        return new Frames(makeFrames());
    }

    private static List<Frame> makeFrames() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.createFirstFrame());
        for (int i = 0; i < RoundNumber.MAX - 1; i++) {
            frames.add(frames.get(i).createNextFrame());
        }
        return frames;
    }

    public List<Frame> value() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isEnded(RoundNumber roundNumber) {
        return getFrame(roundNumber).isEnded();
    }

    public void knockDownPin(RoundNumber roundNumber, Pin pin) {
        getFrame(roundNumber).knockDownPin(pin);
    }

    private Frame getFrame(RoundNumber roundNumber) {
        return frames.get(roundNumber.value() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
