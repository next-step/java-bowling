package bowling.model.frame;

import bowling.model.Pins;
import bowling.utility.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Frames {

    private static final int START_SCORE_VALUE = 0;

    private final LinkedList<Frame> frames;

    private Frames(List<Frame> frames) {
        Assert.notEmpty(frames, "frames must not empty");
        this.frames = new LinkedList<>(frames);
    }

    public static Frames init() {
        return new Frames(Collections.singletonList(NormalFrame.init(FrameNumber.FIRST)));
    }

    static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public boolean isFinished() {
        return frames.getLast().isFinal() && frames.getLast().isEnd();
    }

    public Frames bowling(Pins pins) {
        validateStateToAdd();
        LinkedList<Frame> newFrames = addedBonusPinsFrames(pins);
        Frame last = newFrames.removeLast();
        if (last.isEnd()) {
            newFrames.add(last);
        }
        newFrames.add(last.next(pins));
        return from(newFrames);
    }

    public List<Integer> accumulatedScores() {
        List<Integer> scores = new ArrayList<>();
        int score = START_SCORE_VALUE;
        for (Frame frame : framesWithNoRemainCount()) {
            score += frame.sumPinsCount();
            scores.add(score);
        }
        return scores;
    }

    public List<Frame> list() {
        return Collections.unmodifiableList(this.frames);
    }

    public boolean nextNumberGreaterThan(Frames frames) {
        return nextFrameNumber().isGreaterThan(frames.nextFrameNumber());
    }

    private List<Frame> framesWithNoRemainCount() {
        return this.frames.stream()
                .filter(frame -> !frame.hasRemainCount())
                .collect(Collectors.toList());
    }

    private LinkedList<Frame> addedBonusPinsFrames(Pins pins) {
        return frames.stream()
                .map(frame -> {
                    if (frame.isEnd() && frame.hasRemainCount()) {
                        return frame.addBonusPins(pins);
                    }
                    return frame;
                }).collect(Collectors.toCollection(LinkedList::new));
    }

    private FrameNumber nextFrameNumber() {
        Frame last = this.frames.getLast();
        if (last.isEnd()) {
            return last.number().increase();
        }
        return last.number();
    }

    private void validateStateToAdd() {
        if (isFinished()) {
            throw new IllegalStateException(String.format("frames(%s) is already finished", this));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }
}
