package bowling.domain.pitch;

import java.util.Objects;
import java.util.Optional;

public class Pitch {
    private static final int MIN_COUNT = 0;
    private static final String OVER_COUNT_MESSAGE = "볼링 핀이 음수일 수 없습니다. 다시 확인해주세요.";

    private final int count;

    private Pitch(int count) {
        this.count = count;
    }

    public int count() {
        return this.count;
    }

    public static Pitch of(int count) {
        isOverCount(count);

        return new Pitch(count);
    }

    public Pitch next(int count) {
        int totalCount = Math.addExact(this.count, count);
        isOverCount(totalCount);

        return of(count);
    }

    private static void isOverCount(int count) {
        Optional.of(count)
                .filter(c -> MIN_COUNT <= c)
                .orElseThrow(() -> new IllegalArgumentException(OVER_COUNT_MESSAGE));
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
