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

    default String description() {
        return "";
    }

    int getNextRolledResultMergeScore(RolledResult nextRolledResult);

    ImmutableTwoFallenPins twoFallenPins();
}
