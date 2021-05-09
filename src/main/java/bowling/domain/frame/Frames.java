package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        addHitScore(hitNumber);
        if (isNeededToNext()) {
            addNext();
        }
    }

    public List<Score> totalScores() {
        List<Score> eachScore = frames.stream()
                .filter(frame -> !frame.canAccumulate())
                .map(frame -> frame.totalScore())
                .collect(Collectors.toList());
        for (int i = 1; i < eachScore.size(); i++) {
            eachScore.set(i, eachScore.get(i - 1).calculate(eachScore.get(i)));
        }
        return eachScore;
    }

    public boolean isFinished() {
        return frames.size() == MAX_INDEX && getLast().isFinished();
    }

    public int getLastIndex() {
        return frames.size();
    }

    private void rollOne(HitNumber hitNumber) {
        setLast(getLast().roll(hitNumber));
    }

    private void addHitScore(HitNumber hitNumber) {
        for (int i = 0; i < frames.size() - 1; i++) {
            frames.set(i, hitNumber.addScore(frames.get(i)));
        }
    }

    private boolean isNeededToNext() {
        return frames.size() != MAX_INDEX && getLast().isFinished();
    }

    private void addNext() {
        frames.add(getLast().next(getLastIndex()));
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    private void setLast(Frame frame) {
        frames.set(frames.size() - 1, frame);
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

    @Override
    public String toString() {
        return "" + frames + "";
    }
}
