package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Pitches {

    private static final int SPARE_PITCHES_LIMIT = 2;
    private static final int SPARE_TOTAL = 10;

    protected final List<Pitch> pitches;

    public Pitches() {
        this.pitches = new ArrayList<>();
    }

    public abstract boolean add(final Pitch pitch);

    public abstract boolean isFull();

    public abstract int score(final Frame nextFrame);

    public List<Integer> values() {
        return pitches.stream()
                .map(Pitch::value)
                .collect(Collectors.toList());
    }

    protected int sum() {
        return pitches.stream()
                .map(Pitch::value)
                .reduce(0, Integer::sum);
    }

    protected int sum(final Pitch pitch) {
        return sum() + pitch.value();
    }

    protected boolean isSpare() {
        return pitches.size() >= SPARE_PITCHES_LIMIT
                && sum() == SPARE_TOTAL;
    }

    protected boolean isStrike(final int pitchIndex) {
        return pitches.size() > pitchIndex
                && pitches.get(pitchIndex)
                        .isStrike();
    }

}
