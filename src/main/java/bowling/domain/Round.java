package bowling.domain;

import java.util.Objects;

public class Round {

    public static final int ROUND_START = 1;
    public static final int ROUND_END = 10;
    public static final int ROUND_MAX = ROUND_END + 1;
    private int round;

    public Round() {
        this.round = ROUND_START;
    }

    public Round(int number) {
        validateUpperRoundRange(number);
        this.round = number;
    }

    private void validateUpperRoundRange(int number) {
        if (number < ROUND_START || number > ROUND_END + 1) {
            throw new IllegalArgumentException("Round는 " + ROUND_START + "보다 작거나 "
                    + ROUND_MAX + "보다 클 수 없습니다.");
        }
    }

    public int value() {
        return this.round;
    }

    public int index() {
        return this.round - 1;
    }

    public void nextRound() {
        validateUpperRoundRange(this.round + 1);
        this.round++;
    }

    public boolean isGameEnd() {
        return this.round > ROUND_END;
    }

    public boolean isAfterStartRound() {
        return this.round > ROUND_START;
    }

    public Round beforeRound() {
        return new Round(this.round - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Round round1 = (Round) o;
        return this.round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.round);
    }
}
