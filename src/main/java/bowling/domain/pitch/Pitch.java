package bowling.domain.pitch;

import bowling.domain.pitch.exception.OverCountException;

import java.util.Objects;
import java.util.Optional;

public class Pitch {
    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = 10;

    private final int count;

    private Pitch(int count) {
        this.count = count;
    }

    public int count() {
        return this.count;
    }

    public static Pitch of(int count) {
        isOverCount(MIN_COUNT, count);

        return new Pitch(count);
    }

    public Pitch next(int count) {
        int totalCount = Math.addExact(this.count, count);
        isOverCount(this.count, totalCount);

        return of(count);
    }

    private static void isOverCount(int currentCount, int nextCount) {
        int remainingCount = Math.subtractExact(MAX_COUNT, currentCount);

        Optional.of(nextCount)
                .filter(count -> MIN_COUNT <= count)
                .filter(count -> count <= MAX_COUNT)
                .orElseThrow(() -> new OverCountException(remainingCount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return count == pitch.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
