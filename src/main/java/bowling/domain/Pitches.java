package bowling.domain;

import bowling.domain.status.Status;
import bowling.domain.status.StatusFactory;

import java.util.List;
import java.util.function.Consumer;

public class Pitches {
    private final List<Pitch> pitches;

    public Pitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public int count() {
        return pitches.size();
    }

    public int sum() {
        return sum(pitches.size());
    }

    public int sum(int limit) {
        return pitches.stream()
                .limit(limit)
                .reduce(new Pitch(0), Pitch::sum)
                .intValue();
    }

    public Pitch get(int index) {
        return pitches.get(index);
    }

    public Pitches pitch(int point) {
        Pitch lastPitch = lastPitch();
        pitches.add(lastPitch.pitch(point, pitches.size()));
        return this;
    }

    private Pitch lastPitch() {
        if (pitches.isEmpty()) {
            return new Pitch(0);
        }
        return pitches.get(pitches.size() - 1);
    }

    public boolean isStrike() {
        return pitches.stream().anyMatch(Pitch::isStrike);
    }

    public boolean isSpare() {
        return pitches.stream().anyMatch(Pitch::isSpare);
    }

    public boolean isOpen() {
        return pitches.stream().anyMatch(Pitch::isOpen);
    }

    public boolean isLastPitch(int frameSize) {
        return pitches.size() == frameSize;
    }

    public String display() {
        return status().display(this);
    }

    private Status status() {
        return StatusFactory.status(this);
    }

    public void forEach(Consumer<? super Pitch> action) {
        pitches.forEach(action);
    }

    public Pitch firstPitch() {
        return get(0);
    }
}
