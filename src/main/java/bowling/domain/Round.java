package bowling.domain;

import java.util.Objects;

public class Round {

    public static final int ROUND_START = 1;
    public static final int ROUND_END = 10;
    private int round;

    public Round() {
        this.round = ROUND_START;
    }

    public Round(int number) {
        validateUpperStartRound(number);
        this.round = number;
    }

    private void validateUpperStartRound(int number) {
        if (number < ROUND_START) {
            throw new IllegalArgumentException("Round는 " + ROUND_START + "보다 작을 수 없습니다.");
        }
    }

    public boolean isGameEnd() {
        return this.round > ROUND_END;
    }

    public int value() {
        return this.round;
    }

    public int index() {
        return this.round - 1;
    }

    public void nextRound() {
        this.round++;
    }

    public boolean isFirstRound() {
        return this.round == ROUND_START;
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
