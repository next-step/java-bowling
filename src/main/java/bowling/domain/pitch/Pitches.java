package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;

public class Pitches {

    private static final int MAX_COUNT = 10;
    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;

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

    public void bonus(int count) {
        Pitch bonus = Pitch.of(count);

        pitches.add(bonus);
    }

    public int size() {
        return this.pitches.size();
    }

    public boolean isStrikeOrSpare() {
        return this.isStrike() || this.isSpare();
    }

    public boolean isStrike() {
        return this.firstPitch().count() == MAX_COUNT;
    }

    public boolean isSpare() {
        int firstCount = this.firstPitch().count();
        int secondCount = 0;
        if (this.size() >= SECOND) {
            secondCount = this.secondPitch().count();
        }
        return this.size() >= SECOND && Math.addExact(firstCount, secondCount) == MAX_COUNT;
    }

    public int totalCount() {
        return this.pitches.stream()
                .mapToInt(Pitch::count)
                .sum();
    }

    private Pitch firstPitch() {
        return this.pitches.get(ZERO);
    }

    private Pitch secondPitch() {
        return this.pitches.get(FIRST);
    }

    @Override
    public String toString() {
        return "Pitches{" +
                "pitches=" + pitches +
                '}';
    }
}
