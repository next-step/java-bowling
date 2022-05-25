package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pitches {

    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;

    private final List<Pitch> pitches;

    private Pitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Pitches first(int count) {
        Pitch first = Pitch.of(count);
        List<Pitch> pitches = new ArrayList<>();
        pitches.add(first);

        return new Pitches(pitches);
    }

    public Pitches next(int count) {
        Pitch second = this.firstPitch().next(count);
        this.pitches.add(second);

        return this;
    }

    public Pitch firstPitch() {
        return this.pitches.get(ZERO);
    }

    public Pitch secondPitch() {
        return Optional.of(this.pitches.size())
                .filter(size -> size == SECOND)
                .map(size -> this.pitches.get(FIRST))
                .orElseGet(Pitch::zero);
    }

    protected int totalCount() {
        int firstCount = this.firstPitch().count();
        int secondCount = this.secondPitch().count();

        return Math.addExact(firstCount, secondCount);
    }
}
