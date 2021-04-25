package bowling.domain.frame;

import bowling.exception.InvalidRoundNumberException;

import java.util.Objects;

public final class RoundNumber {

    public static final int MAX = 10;
    public static final RoundNumber MAX_ROUND_NUMBER = new RoundNumber(MAX);
    private static final int MIN = 1;
    private static final int FIRST_ROUND_NUMBER = 1;

    private final int roundNumber;

    public RoundNumber(int roundNumber) {
        validateRoundNumber(roundNumber);
        this.roundNumber = roundNumber;
    }

    public static RoundNumber firstRoundNumber() {
        return new RoundNumber(FIRST_ROUND_NUMBER);
    }

    private void validateRoundNumber(int roundNumber) {
        if (roundNumber < MIN || roundNumber > MAX) {
            throw new InvalidRoundNumberException(roundNumber);
        }
    }

    public int value() {
        return roundNumber;
    }

    public RoundNumber nextRoundNumber() {
        return new RoundNumber(roundNumber + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundNumber that = (RoundNumber) o;
        return roundNumber == that.roundNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundNumber);
    }
}
