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
        if (isCompleteState()) {
            return twoFallenPins().totalScore();
        }
        return 0;
    }

    default int getNextRolledResultMergeScore(RolledResult nextRolledResult){
        return 0;
    }

    default String gutterOrFallenPinValue(int rollingAttemptCount) {
        final ImmutableTwoFallenPins twoFallenPins = twoFallenPins();
        if (FallenPins.empty().equals(twoFallenPins.getPins(rollingAttemptCount))){
            return "?";
        }
        if (twoFallenPins.isGutter(rollingAttemptCount)){
            return "-";
        }
        return String.valueOf(twoFallenPins.getFallenPins(rollingAttemptCount));
    }

    ImmutableTwoFallenPins twoFallenPins();

    default RolledResult nextRolledResult(int fallenPinsValue){
        return notAtRolledResult();
    }
}
