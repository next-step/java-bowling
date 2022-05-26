package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pitches {

    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int MAX_COUNT = 10;

    private final List<Pitch> pitches;

    private Pitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Pitches first(int count) {
        List<Pitch> pitches = new ArrayList<>();
        Pitch first = Pitch.of(count);
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

    public int size() {
        return this.pitches.size();
    }

    public boolean isBonus() {
        return this.totalCount() == MAX_COUNT;
    }

    public int totalCount() {
        int firstCount = this.firstPitch().count();
        int secondCount = this.secondPitch().count();

        return Math.addExact(firstCount, secondCount);
    }

    @Override
    public String toString() {
        return "Pitches{" +
                "pitches=" + pitches +
                '}';
    }
}
