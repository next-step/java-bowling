package bowling.domain;

import bowling.exception.RoundRangeException;

import java.util.Objects;

public class Round {

    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;
    private static final int BEFORE_DELIMITER = 2;
    private static final int BEFORE_SECOND_DELIMITER = 3;
    private static final int ROUND_MINIMUM_BOUND = 0;
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
            throw new RoundRangeException(ROUND_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static Round firstRound() {
        return new Round(FIRST_ROUND);
    }

    public static Round finalRound() {
        return new Round(FINAL_ROUND);
    }

    public Round nextRound() {
        if (this.round < FINAL_ROUND) {
            return from(this.round + 1);
        }
        return this;
    }

    public boolean isFinalRound() {
        return round == FINAL_ROUND;
    }

    public int round() {
        return round;
    }

    public int before() {
        return round - BEFORE_DELIMITER;
    }

    public int beforeSecond() {
        return round - BEFORE_SECOND_DELIMITER;
    }

    public int now() {
        return round - 1;
    }

    public boolean hasBefore() {
        return round - BEFORE_DELIMITER >= ROUND_MINIMUM_BOUND;
    }

    public boolean hasBeforeSecond() {
        return round - BEFORE_SECOND_DELIMITER >= ROUND_MINIMUM_BOUND;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
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
}