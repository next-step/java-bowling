package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;

public class Pitches {

    private static final int ZERO = 0;
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

    public int size() {
        return this.pitches.size();
    }

    public boolean isStrikeOrSpare() {
        return this.totalCount() == MAX_COUNT;
    }

    public int totalCount() {
        return this.pitches.stream()
                .mapToInt(Pitch::count)
                .sum();
    }

    @Override
    public String toString() {
        return "Pitches{" +
                "pitches=" + pitches +
                '}';
    }
}
