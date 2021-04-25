package bowling.domain;

import java.util.Objects;

public class Round {

    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;
    private static final String ROUND_RANGE_EXCEPTION_MESSAGE = "라운드는 1 이상 10 이하 입니다";

    private final int round;

    public Round(int round) {
        this.round = round;
    }

    public static Round from(int round) {
        validateRound(round);
        return new Round(round);
    }

    private static void validateRound(int round) {
        if (round < FIRST_ROUND || round > FINAL_ROUND) {
            throw new IllegalArgumentException(ROUND_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static Round firstRound() {
        return new Round(FIRST_ROUND);
    }

    public static Round finalRound() {
        return new Round(FINAL_ROUND);
    }

    public Round nextRound() {
        return from(this.round + 1);
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
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }

    public boolean isFinalRound() {
        return round == FINAL_ROUND;
    }
}
