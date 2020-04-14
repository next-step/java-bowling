package bowling.domain.frame;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.Objects;

public class Pitch {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "점수는 %d에서 %d 사이여야 합니다.";
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Pitch[] cache = new Pitch[11];

    static {
        for (int i = MIN; i <= MAX; i++) {
            cache[i] = new Pitch(i);
        }
    }

    private int count;
    private boolean spare;

    private Pitch(int count, boolean spare) {
        this.count = count;
        this.spare = spare;
    }

    public Pitch(int count) {
        this(count, false);
    }

    public Pitch(Pitch pitch) {
        this.count = pitch.count;
        this.spare = pitch.spare;
    }

    public int add(int pinCount) {
        return this.count + pinCount;
    }

    public boolean isOverMaxAfterAdd(int pinCount) {
        return add(pinCount) > MAX;
    }

    public boolean isMax() {
        return count == MAX;
    }

    public int getCount() {
        return count;
    }

    public static Pitch empty() {
        return cache[0];
    }

    public boolean isStrike() {
        return count == MAX;
    }

    public boolean isSpare() {
        return spare;
    }

    public boolean isGutter() {
        return count == MIN;
    }

    public Pitch next(int pinCount) {
        if (add(pinCount) == MAX) {
            return new Pitch(pinCount, true);
        }
        return new Pitch(pinCount);
    }

    public static Pitch valueOf(int pinCount) {
        if (pinCount < MIN || pinCount > MAX) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MIN, MAX));
        }

        return cache[pinCount];
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return count == pitch.count &&
                spare == pitch.spare;
    }

    @Override public int hashCode() {
        return Objects.hash(count, spare);
    }
}
