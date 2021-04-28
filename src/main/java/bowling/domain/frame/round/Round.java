package bowling.domain.frame.round;

import java.util.Objects;

public abstract class Round {

    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;

    private final int round;

    protected Round(int round) {
        this.round = round;
    }

    public static Round of(int round) {
        if (round == FINAL_ROUND) {
            return FinalRound.of(round);
        }
        return NormalRound.of(round);
    }

    public int round() {
        return round;
    }

    public Round next() {
        if (round == FINAL_ROUND - FIRST_ROUND) {
            return FinalRound.of(FINAL_ROUND);
        }
        return NormalRound.of(this.round + 1);
    }

    public static Round first() {
        return NormalRound.of(FIRST_ROUND);
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
}
