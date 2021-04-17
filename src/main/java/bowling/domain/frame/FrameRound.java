package bowling.domain;

import java.util.Objects;

public class FrameRound {
    public static final int MIN_ROUND = 1;
    public static final int MAX_ROUND = 10;
    public static final int ADD = 1;

    private final int round;

    public FrameRound(int round) {
        this.round = round;
        validate();
    }

    public static FrameRound get(int round) {
        return new FrameRound(round);
    }

    private void validate() {
        minValidate();
        maxValidate();
    }

    private void minValidate() {
        if (round < MIN_ROUND) {
            throw new IllegalArgumentException("각 프레임은 1 이상입니다");
        }
    }

    private void maxValidate() {
        if (round > MAX_ROUND) {
            throw new IllegalArgumentException("각 프레임은 10을 초과할 수 없습니다.");
        }
    }

    public static FrameRound start() {
        return new FrameRound(MIN_ROUND);
    }

    public static FrameRound last() {
        return new FrameRound(MAX_ROUND);
    }

    public FrameRound next() {
        return new FrameRound(this.round + ADD);
    }

    public int getRound() {
        return round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameRound that = (FrameRound) o;
        return round == that.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }
}
