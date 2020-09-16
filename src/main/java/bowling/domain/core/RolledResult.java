package bowling.domain.core;

import bowling.domain.core.state.ImmutableTwoFallenPins;

import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;

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

    default int getRolledResultScore(){
        return getNextRolledResultMergeScore(notAtRolledResult());
    }

    int getNextRolledResultMergeScore(RolledResult nextRolledResult);

    ImmutableTwoFallenPins twoFallenPins();
}
