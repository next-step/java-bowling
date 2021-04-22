package bowling.domain.attempt;

public interface AttemptNumber {
    int increase();
    boolean isSecondAttempt();
    boolean isFirstAttempt();
    boolean isLastAttempt();
    boolean isBonusAttempt();
}
