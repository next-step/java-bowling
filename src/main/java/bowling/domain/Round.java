package bowling.domain;

import bowling.global.BowlingConst;
import java.util.Objects;

public class Round {

    private int round;

    public Round() {
        this.round = BowlingConst.ROUND_START;
    }

    public Round(int number) {
        this.round = number;
    }

    public boolean isGameEnd() {
        return this.round > BowlingConst.ROUND_END;
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
