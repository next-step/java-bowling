package bowling.domain.attempt;

import java.util.Objects;

public class FinalAttemptNumber implements AttemptNumber {
    private static final int BONUS_ATTEMPT_NUMBER = 3;
    private static final int LAST_ATTEMPT_NUMBER = 2;
    private static final int FIRST_ATTEMPT = 0;

    private final int attemptNumber;

    private FinalAttemptNumber(int attemptNumber) {
        validate(attemptNumber);
        this.attemptNumber = attemptNumber;
    }

    public static FinalAttemptNumber of(int attemptNumber) {
        return new FinalAttemptNumber(attemptNumber);
    }

    public static FinalAttemptNumber first() {
        return new FinalAttemptNumber(FIRST_ATTEMPT);
    }

    private void validate(int attemptNumber) {
        if (attemptNumber < FIRST_ATTEMPT || attemptNumber > BONUS_ATTEMPT_NUMBER) {
            throw new IllegalArgumentException("유효한 투구 시도 횟수가 아닙니다.");
        }
    }

    @Override
    public int increase() {
        if (this.attemptNumber == BONUS_ATTEMPT_NUMBER) {
            throw new IllegalStateException("보너스 투구 기회가 존재하지 않아 증가할수 없습니다.");
        }
        return this.attemptNumber + 1;
    }

    @Override
    public boolean isFirstAttempt() {
        return this.attemptNumber == FIRST_ATTEMPT;
    }

    @Override
    public boolean isLastAttempt() {
        return this.attemptNumber >= LAST_ATTEMPT_NUMBER;
    }

    @Override
    public boolean isBonusAttempt() {
        return this.attemptNumber == BONUS_ATTEMPT_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinalAttemptNumber that = (FinalAttemptNumber)o;
        return attemptNumber == that.attemptNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attemptNumber);
    }
}
