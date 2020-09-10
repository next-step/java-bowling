package bowling.domain.core;

public interface RolledResult {
    default boolean isCompleteState() {
        return true;
    }

    default boolean isNotCompleteState() {
        return !isCompleteState();
    }

    default int tryCountByTerminateFrame() {
        return 1;
    }

    String description();

    int numberOfPinsFallingByAttemptCount(int rollingAttemptCount);
}
