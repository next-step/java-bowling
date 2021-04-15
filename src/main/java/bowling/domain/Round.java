package bowling.domain;

import java.util.Objects;

public class Round {

    private final int round;

    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;

    private Round(int round) {
        this.round = round;
    }

    public static Round first() {
        return new Round(FIRST_ROUND);
    }

    public static Round of(int position) {
        return new Round(position);
    }

    public Round next() {
        return new Round(this.round + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round1 = (Round) o;
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }

    public boolean isFinal() {
        return round == FINAL_ROUND;
    }
}
