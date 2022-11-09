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
        this.round = number;
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
