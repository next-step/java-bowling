package bowling.domain.pitch;

import java.util.Objects;
import java.util.Optional;

public class Pitch {
    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = 10;
    private static final String OVER_COUNT_MESSAGE = "현재 남은 볼링 핀은 %s 개 입니다. 다시 확인해주세요.";

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
        String message = String.format(OVER_COUNT_MESSAGE, remainingCount);
        Optional.of(nextCount)
                .filter(count -> MIN_COUNT <= count)
                .filter(count -> count <= MAX_COUNT)
                .orElseThrow(() -> new IllegalArgumentException(message));
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

    @Override
    public String toString() {
        return "Pitch{" +
                "count=" + count +
                '}';
    }
}
