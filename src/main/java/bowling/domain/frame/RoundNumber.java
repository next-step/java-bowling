package bowling.domain.frame;

import bowling.exception.InvalidRoundNumberException;

import java.util.Objects;

public final class RoundNumber {

    private static final int MIN = 1;
    private static final int MAX = 10;

    private final int roundNumber;

    public RoundNumber(int roundNumber) {
        validateRoundNumber(roundNumber);
        this.roundNumber = roundNumber;
    }

    private void validateRoundNumber(int roundNumber) {
        if (roundNumber < MIN || roundNumber > MAX) {
            throw new InvalidRoundNumberException(roundNumber);
        }
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
