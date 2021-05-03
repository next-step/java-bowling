package bowling.domain.frame;

import bowling.domain.HitNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frames {
    private static final int MAX_INDEX = 10;
    private final List<Frame> frames = new ArrayList<>();

    private Frames() {
        frames.add(NormalFrame.of());
    }

    public static Frames of() {
        return new Frames();
    }

    public void play(HitNumber hitNumber) {
        rollOne(hitNumber);
        if(isNeededToNext()) {
            addNext();
        }
    }

    private void rollOne(HitNumber hitNumber) {
        setLast(getLast().roll(hitNumber));
    }

    private boolean isNeededToNext() {
        return frames.size() != MAX_INDEX && getLast().isFinished();
    }

    private void addNext() {
        frames.add(getLast().next(getLastIndex()));
    }

    public boolean isFinished() {
        return frames.size() == MAX_INDEX && getLast().isFinished();
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    private void setLast(Frame frame) {
        frames.set(frames.size() - 1, frame);
    }

    public int getLastIndex() {
        return frames.size() - 1;
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
