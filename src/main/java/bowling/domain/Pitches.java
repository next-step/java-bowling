package bowling.domain;

import bowling.domain.status.Status;
import bowling.domain.status.StatusFactory;
import bowling.domain.status.Symbol;

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
        pitches.add(new Pitch(point));
        return this;
    }

    public boolean isStrike() {
        return status().keyword() == Symbol.STRIKE;
    }

    public Status status() {
        return StatusFactory.status(this);
    }

    public String display() {
        return status().display(this);
    }

    public void forEach(Consumer<? super Pitch> action) {
        pitches.forEach(action);
    }

    public Pitch firstPitch() {
        return get(0);
    }
}
