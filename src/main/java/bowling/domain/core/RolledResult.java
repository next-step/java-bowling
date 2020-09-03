package bowling.domain.core;

public interface RolledResult {
    default boolean isCompleteState() {
        return true;
    }

    default boolean isNotCompleteState() {
        return !isCompleteState();
    }

    default boolean canNotSpendRemainingPins(int fallenPins) {
        return false;
    }

    default int tryCountByTerminateFrame() {
        return 1;
    }

    String description();

    int countOfFallenPinsByRolls(int rollingTryCount);
}
