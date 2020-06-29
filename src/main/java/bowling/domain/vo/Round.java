package bowling.domain.vo;

public class Round {
    private static final int ROUND_START_BOUND = 1;
    private static final int ROUND_END_BOUND = 10;
    private final int value;

    public Round(final int value) {
        if (value < ROUND_START_BOUND || value > ROUND_END_BOUND) {
            throw new IllegalArgumentException("라운드는 1-10 사이여야합니다.");
        }
        this.value = value;
    }

    public Round next() {
        return new Round(this.value + 1);
    }

    public boolean isFinal() {
        return this.value == ROUND_END_BOUND;
    }
}
