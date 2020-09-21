package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

public final class Spare implements RolledResult {
    private final ImmutableTwoFallenPins twoFallenPins;

    Spare(ImmutableTwoFallenPins twoFallenPins) {
        this.twoFallenPins = twoFallenPins;
    }

    public static RolledResult expectSpareAfterBonusBowl(int rollingAttemptCount, RolledResult rolledResult){
        if (isFinalTryCount(rollingAttemptCount) && rolledResult.isNotCompleteState()){
            return new SpareAfterBonusBowl(rolledResult);
        }
        return rolledResult;
    }

    private static boolean isFinalTryCount(int rollingAttemptCount) {
        return (TerminateFrame.MAX_TRY_COUNT_SIZE - 1) == rollingAttemptCount;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return FallenPins.MAX_FALLEN_PIN_COUNT
            + nextRolledResult.twoFallenPins()
                              .firstFallenPinsValue();
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return twoFallenPins;
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinValue(0));
    }
}
