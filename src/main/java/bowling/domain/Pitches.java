package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Pitches {
    private final List<Pitch> pitches;

    public Pitches() {
        this.pitches = new ArrayList<>();
    }

    public int sum() {
        return IntStream.range(0, pitches.size())
                .map(index -> pitches.get(index).intValue())
                .sum();
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

    public void forEach(Consumer<? super Pitch> action) {
        pitches.forEach(action);
    }

    public boolean isEnd() {
        return last().isEnd();
    }

    public boolean hasBonusPitch() {
        return last().hasBonusPitch();
    }

    private Pitch last() {
        return pitches.get(pitches.size() - 1);
    }
}
