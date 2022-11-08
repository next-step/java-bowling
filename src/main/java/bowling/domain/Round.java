package bowling.domain;

import java.util.Objects;

public class Round {

    private static final int START_ROUND = 1;
    private static final int LAST_ROUND = 10;
    private int round;

    public Round() {
        this.round = START_ROUND;
    }

    public Round(int number) {
        this.round = number;
    }

    public boolean isGameEnd(Frame frame) {
        if (!frame.isRemainChance()) {
            this.round += 1;
        }
        if (this.round > LAST_ROUND) {
            return true;
        }
        if (frame.isRemainChance()) {
            return false;
        }
        return true;
    }

    public int value() {
        return this.round;
    }

    public int index() {
        return this.round - 1;
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
