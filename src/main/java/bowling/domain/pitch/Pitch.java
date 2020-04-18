package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.Objects;

public class Pitch {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "점수는 %d에서 %d 사이여야 합니다.";
    protected static final int MIN = 0;
    protected static final int MAX = 10;
    private static final Pitch[] cache = new Pitch[11];

    static {
        for (int i = MIN; i <= MAX; i++) {
            cache[i] = new Pitch(i);
        }
    }

    private int count;
    private State state = new Ready();

    private Pitch(int count, State state) {
        this.count = count;
        this.state = state;
    }

    private Pitch(int count) {
        this.count = count;
        this.state = state.bowl(count);
    }

    public Pitch(Pitch pitch) {
        this.count = pitch.count;
        this.state = pitch.state;
    }

    public int add(int pinCount) {
        return this.count + pinCount;
    }

    public boolean isOverMaxAfterAdd(int pinCount) {
        return add(pinCount) > MAX;
    }

    public int getCount() {
        return count;
    }

    public static Pitch empty() {
        return cache[0];
    }

    public boolean isStrike() {
        return state.getClass() == Strike.class;
    }

    public boolean isSpare() {
        return state.getClass() == Spare.class;
    }

    public boolean isGutter() {
        return count == MIN;
    }

    public Pitch next(int pinCount) {
        return new Pitch(pinCount, this.state.bowl(pinCount));
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
                Objects.equals(state, pitch.state);
    }

    @Override public int hashCode() {
        return Objects.hash(count, state);
    }
}
