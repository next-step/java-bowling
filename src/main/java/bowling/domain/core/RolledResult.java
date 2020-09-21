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

    default int getNextRolledResultMergeScore(RolledResult nextRolledResult){
        return twoFallenPins().totalScore();
    }

    default String gutterOrFallenPinValue(int rollingAttemptCount) {
        final ImmutableTwoFallenPins twoFallenPins = twoFallenPins();
        if (twoFallenPins.isGutter(rollingAttemptCount)){
            return "-";
        }
        return String.valueOf(twoFallenPins.getFallenPins(rollingAttemptCount));
    }

    ImmutableTwoFallenPins twoFallenPins();
}
