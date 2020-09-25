package bowling.domain.core;

import bowling.domain.core.state.ImmutableTwoFallenPins;
import bowling.domain.core.state.Score;

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

    Score getScore();

    Score calculateScore(Score score);

    ImmutableTwoFallenPins twoFallenPins();

    default RolledResult nextRolledResult(int fallenPinsValue){
        return notAtRolledResult();
    }
}
