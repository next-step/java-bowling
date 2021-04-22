package bowling.domain.attempt;

public interface AttemptNumber {
    int increase();
    boolean isFirstAttempt();
    boolean isLastAttempt();
    boolean isBonusAttempt();
}
